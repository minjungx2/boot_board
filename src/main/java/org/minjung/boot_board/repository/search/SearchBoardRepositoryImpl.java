package org.minjung.boot_board.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.QBoard;
import org.minjung.boot_board.entity.QMember;
import org.minjung.boot_board.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl(){
        super(Board.class);
    }
    @Override
    public Board search1() {
        log.warn("search1..................");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        //select * from Board b
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        BooleanExpression ex1 = board.bno.eq(99L);
        //select b from Board b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member,reply.count()).where(ex1).groupBy(board);

        log.warn("---------------------------------");
        log.warn(jpqlQuery);
        log.warn("---------------------------------");

        List<Tuple> result = tuple.fetch();
        log.warn("==================================");
        Long count = jpqlQuery.fetchCount();
        log.warn(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.warn("========================searchPage============================");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member,reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression booleanExpression = board.bno.gt(0L);

        //and bno > 0
        booleanBuilder.and(booleanExpression);

        //where(board.title like ... or memeber.name like ... or reply.text like ...)
        if(type != null){
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            String[] typeArr = type.split("");

            for(String t:typeArr){
                switch (t){
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.name.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(reply.text.contains(keyword));
                        break;
                }//end switch
            }//end for

            booleanBuilder.and(conditionBuilder);

        }//end type if

        //---------------------------------------------------------------
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            //bno
            String prop = order.getProperty();
            log.warn("Sort prop: " + prop);

            Order direction = order.isAscending()?Order.ASC:Order.DESC;

            PathBuilder<Board> orderByExpression = new PathBuilder<Board>(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.where(booleanBuilder);

        //group by
        tuple.groupBy(board);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());
        log.warn(tuple);

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        List<Object[]> resultList = result.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());

        return new PageImpl<Object[]>(resultList, pageable, count);
    }
}
