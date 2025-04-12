package org.integration.api;

import org.integration.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 接收来自院系A的学生数据
     * @param xmlData 符合集成服务器统一格式的XML
     * @return 处理结果
     */
    @PostMapping("/api/students")
    public ResponseEntity<String> receiveStudentData(@RequestBody String xmlData) {
        try {
            // 1. 验证XML格式
            studentService.validateXml(xmlData, "schemas/formatStudent.xsd");

            // 2. 处理业务逻辑（如存储或转发）
            studentService.processStudentData(xmlData);

            return ResponseEntity.ok("数据接收成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("错误: " + e.getMessage());
        }
    }
}