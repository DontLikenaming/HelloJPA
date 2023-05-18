package dsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Department;
import model.Employee;
import model.QDepartment;
import model.QEmployee;
import org.apache.commons.lang.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HelloQueryDSL02 {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();

        try{
            // 쿼리 객체 준비
            QEmployee qemp = QEmployee.employee;
            QDepartment qdept = QDepartment.department;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 전체 사원 데이터 조회
/*            List<Employee> emps = query.selectFrom(qemp).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 일부 사원 데이터 조회 - 페이징 (offset, limit)
/*            List<Employee> emps = query.selectFrom(qemp).offset(30).limit(15).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 사원 데이터 조회 : 이름, 부서번호, 입사일
/*            List<Tuple> items = query.select(qemp.fname, qemp.deptid, qemp.hdate).from(qemp).fetch();


            for(Tuple item : items) {
                System.out.println(item);
            }*/


            // 정렬 : orderby, 부서번호 기준
/*            List<Employee> emps = query.selectFrom(qemp).orderBy(qemp.deptid.desc()).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 조건 검색 : 직책이 IT_PROG인 사원 조회
/*            List<Employee> emps = query.selectFrom(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 조건 검색 : 연봉이 14000 이상인 사원 조회
/*            List<Employee> emps = query.selectFrom(qemp).where(qemp.sal.goe(14000)).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 사원 직책의 수 조회 1
/*            List<Long> cnts = query.select(qemp.jobid.count()).from(qemp).fetch();

            System.out.println(cnts);*/


            // 사원 직책의 수 조회 2
/*            Long cnts = query.select(qemp.jobid).from(qemp).fetchCount();

            System.out.println(cnts);*/


            // 사원 직책의 수 조회 3
/*            List<Tuple> cnts = query.select(qdept.dname, qemp.jobid.countDistinct())
            .from(qemp).groupBy(qdept.dname).fetch();

            for(Tuple cnt : cnts) {
                System.out.println(cnt);
            }*/


            // 사원 직책의 수 조회 4
/*            Long cnts = query.select(qemp.jobid).distinct().from(qemp).fetchCount();

            System.out.println(cnts);*/


            // 직책별 최대, 최소, 평균, 직책 수 조회
/*            StringPath jbcnt = Expressions.stringPath("jbcnt");    // 별칭 정의

            List<Tuple> items = query.select(qemp.jobid, qemp.sal.max(),
                                qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count().as("jbcnt"))
                                .from(qemp).groupBy(qemp.jobid).orderBy(jbcnt.desc()).fetch();

            for(Tuple item : items) {
                System.out.println(item);
            }*/


            // 평균 연봉보다 작게 받는 사원들 조회
/*            JPQLQuery<Double> subqry = JPAExpressions.select(qemp.sal.avg()).from(qemp);
            List<Double> avgsal = query.select(qemp.sal.avg()).from(qemp).fetch();
            List<Employee> items = query.selectFrom(qemp).where(qemp.sal.lt(subqry))
                    .orderBy(qemp.sal.desc()).fetch();

            System.out.println(avgsal);
            for(Employee item : items) {
                System.out.println(item);
            }*/

            // IT 부서에 근무중인 사원들의 이름, 직책, 급여 조회
/*            JPQLQuery<Long> subqry = JPAExpressions.select(qdept.deptid)
                            .where(qdept.dname.eq("IT")).from(qdept);

            List<Tuple> items = query.select(qemp.fname, qemp.jobid, qemp.sal)
                    .where(qemp.deptid.eq(subqry)).from(qemp).fetch();

            for(Tuple item : items) {
                System.out.println(item);
            }*/


            // 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
/*            List<Tuple> items = query.select(qemp.fname, qemp.jobid, qdept.dname)
                    .from(qemp).join(qemp.department, qdept)
                    .where(qemp.deptid.eq(new Long(60))).orderBy(qemp.fname.asc()).fetch();

            for(Tuple item : items) {
                System.out.println(item);
            }*/


            // 제공된 이름, 직책, 연봉으로 사원 조회
            String fname = "Ste";
            String jobid = null;
            Integer sal = null;

            List<Employee> emps = query.selectFrom(qemp)
                    .where(
                        StringUtils.isNotEmpty(fname) ? qemp.fname.contains(fname) : null,
                        StringUtils.isNotEmpty(jobid) ? qemp.jobid.eq(jobid) : null,
                        (sal != null) ? qemp.sal.goe(sal) : null
                    ).fetch();

            for(Employee emp : emps) {
                System.out.println(emp);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
            }
        }
}
