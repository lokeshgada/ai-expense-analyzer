package com.lokesh.ai_expense_analyzer.file;

import com.lokesh.ai_expense_analyzer.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final ExpenseService expenseService;

    // Upload a file and convert rows into Expense records
    // For now we support CSV only (simple format)
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            int savedCount = expenseService.importExpensesFromCsv(file);
            return ResponseEntity.ok("Uploaded successfully. Expenses saved: " + savedCount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}