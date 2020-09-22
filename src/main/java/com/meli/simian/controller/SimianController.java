package com.meli.simian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.simian.model.DnaDto;
import com.meli.simian.model.DnaStatistics;
import com.meli.simian.service.SimianService;
import com.meli.simian.service.StatisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Permite realizar operações com Simians")
@RestController
public class SimianController {

	@Autowired
	private SimianService simianService;
	
	@Autowired
	private StatisticService statisticService;
	
	@ApiOperation("Operação para verificar se uma sequencia de DNA pertence a um Simian.")
	@RequestMapping(method = RequestMethod.POST, value = "/simian", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> verifyIfDnaBelongsToSimian(@RequestBody final DnaDto dto) {
 
        final boolean isSimian = simianService.isSimian(dto.getDna());
        
        if (isSimian) {
        	return ResponseEntity.status(HttpStatus.OK).body(isSimian);
        } else {
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(isSimian);
        }
    }
	
	@ApiOperation("Operação que retorna as estatísticas de verificações de DNA.")
	@RequestMapping(method = RequestMethod.GET, value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DnaStatistics> getStatistics() {
 
        final DnaStatistics statistics = statisticService.calculateStatistics();
 
       	return ResponseEntity.status(HttpStatus.OK).body(statistics);
      
    }
}
