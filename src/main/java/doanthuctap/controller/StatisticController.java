package doanthuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.response.StatisticResponse;
import doanthuctap.service.StatisticService;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatistic(@PathVariable Integer id) {
        try {
            StatisticResponse response = statisticService.getStatistic(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()));
        }
    }

}
