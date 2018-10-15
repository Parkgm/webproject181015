package kr.ac.dit.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.ac.dit.domain.StudentVO;
@Repository
public class StudentDAOImpl implements StudentDAO {
	 //01.JdbcTemplate jdbcTemplate추가
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	//02. 이전 길었던 쿼리문 update쿼리문 한줄로 변경
	@Override
	public void insertStudent(StudentVO studentVO) throws Exception {
		jdbcTemplate.update("insert into student values(?,?)", studentVO.getNo(), studentVO.getName());
	}
	
	@Override
	public List<StudentVO> selectStudentList() throws Exception {
		List<StudentVO> items = new ArrayList<StudentVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "201710477", "201710477");
		PreparedStatement pstmt = conn.prepareStatement("select * from student");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			StudentVO item = new StudentVO();
			item.setNo(rs.getString("no"));
			item.setName(rs.getString("name"));
			items.add(item);
		}
		pstmt.close();
		rs.close();
		conn.close();
		return items;
	}
}
