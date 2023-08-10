package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.commands.RecordCommand;
import hr.tvz.financije.services.RecordService;
import hr.tvz.financije.services.models.RecordDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class RecordController {

    private final RecordService service;

    @GetMapping
    public ResponseEntity<List<RecordDto>> getRecords() {
        return ResponseEntity.ok(service.getRecords());
    }

    @PostMapping
    public ResponseEntity<RecordDto> saveRecord(@RequestBody @Valid RecordCommand command) {
        return ResponseEntity.ok(service.saveRecord(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecordById(@PathVariable int id) {
        service.deleteRecordById(id);
        return ResponseEntity.noContent().build();
    }
}
