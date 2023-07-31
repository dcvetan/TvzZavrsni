package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.RecordCommand;
import hr.tvz.financije.services.RecordService;
import hr.tvz.financije.services.models.Record;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService service;

    @GetMapping
    public ResponseEntity<List<Record>> getRecords() {
        return ResponseEntity.ok(service.getRecords());
    }

    @PostMapping
    public ResponseEntity<Record> saveRecord(@RequestBody @Valid RecordCommand command) {
        return ResponseEntity.ok(service.saveRecord(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecordById(@PathVariable int id) {
        service.deleteRecordById(id);
        return ResponseEntity.noContent().build();
    }
}
