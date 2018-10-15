package kr.ac.dit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.ac.dit.domain.StudentVO;
import kr.ac.dit.service.StudentService;
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public void createGET() throws Exception {
	}
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public void createPOST(StudentVO studentVO, Model model) throws Exception {
		studentService.createStudent(studentVO);
	}
	@RequestMapping("read")
	public void readList(Model model) throws Exception {
		model.addAttribute("items", studentService.readStudentList());
	}
}