package hbm;

import model.Department;
import model.Employee;
import notmap.Empinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;


public class HelloQueryHBM04 {
    public static void main(String[] args){
        // SessionFactory 초기화
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class).buildSessionFactory();
        Session sess = sf.openSession();

        try{
            // 전체 - 사원 조회
/*            Query query = sess.createQuery("from Employee ");
            List<Employee> sjs = query.getResultList();

             System.out.println(sjs);*/


             //조회 - 부분 조회 1 : 이름, 연봉
/*            Query query = sess.createQuery("select fname, sal from Employee ");
            List<Objects[]> items = query.getResultList();

            for(Object[] item : items){
                System.out.println(item[0] + " / " + item[1]);
            }*/


            //조회 - 부분 조회 2 :
/*            Query query = sess.createQuery("select new notmap.Empinfo(fname, sal) from Employee ");
            List<Empinfo> items = query.getResultList();

            for(Empinfo item : items){
                System.out.println(item);
            }*/


            // 조건검색 : where, 직책이 IT_PROG인 사원 조회
/*            Query query = sess.createQuery("from Employee where jobid = ?1");
            query.setParameter(1, "IT_PROG");
            List<Employee> emps = query.getResultList();

            for(Employee emp :emps) {
                System.out.println(emp);
            }*/


            // 조건검색 : 연봉이 20000 이상인 사원 조회
/*            Query query = sess.createQuery("from Employee where sal >= ?1");
            query.setParameter(1, 20000);
            List<Employee> emps = query.getResultList();

            for(Employee emp :emps) {
                System.out.println(emp);
            }*/


            // 정렬 : orderby, 부서번호 기준
/*            Query query = sess.createQuery("from Employee order by deptid desc ");
            List<Employee> emps = query.getResultList();

            for(Employee emp :emps) {
                System.out.println(emp);
            }*/


            // 직책 수 조회
/*            String hql = "select distinct jobid from Employee ";
            Query query = sess.createQuery(hql);
            List cntjob = query.getResultList();

            System.out.println(cntjob);*/


            // 그룹핑 : 직책별 최대, 최소, 평균 연봉, 직책수 조회
/*            String hql = "select jobid, max(sal), min(sal), avg(sal), count(jobid) from Employee group by jobid order by count(jobid) desc";
            Query query = sess.createQuery(hql);
            List<Object[]> emps = query.getResultList();

            for(Object[] emp :emps) {
                System.out.println("직책명 : " + emp[0] + ", 최대 연봉 : " + emp[1] + ", 최소 연봉 : " + emp[2]
                                   + ", 평균 연봉 : " + emp[3] + ", 인원 수 : " + emp[4]);
            }*/


            // 서브쿼리 1 : 평균 연봉보다 적게 받는 사원의 이름과 연봉 조회
/*            String hql = "select fname, sal from Employee where sal < ( select avg(sal) from Employee) ";
            Query query = sess.createQuery(hql);
            List<Object[]> emps = query.getResultList();

            for(Object[] emp :emps) {
                System.out.println("이름 : " + emp[0] + ", 연봉 : " + emp[1]);
            }*/


            // 서브쿼리 2 : IT 부서에 근무중인 사원들의 이름, 직책, 연봉 조회
/*            String hql = " select fname, jobid, sal from Employee where deptid
                            < ( select deptid from Department where dname = 'IT' )";
            Query query = sess.createQuery(hql);
            List<Object[]> emps = query.getResultList();

            for(Object[] emp :emps) {
                System.out.println("이름 : " + emp[0] + ", 직책 : " + emp[1] + ", 연봉 : " + emp[2]);
            }*/


            // join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
            String hql = " select e.fname, e.jobid, d.dname from Employee e inner join Department d " +
                    " on e.deptid = d.deptid where e.deptid = 60 ";
            Query query = sess.createQuery(hql);
            List<Object[]> emps = query.getResultList();

            for(Object[] emp :emps) {
                System.out.println("이름 : " + emp[0] + ", 직책 : " + emp[1] + ", 부서명 : " + emp[2]);
            }





        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            }
        }
}
