package com.doc.ssa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doc.ssa.dto.AppointmentDto;
import com.doc.ssa.dto.ListAppointmentDto;
import com.doc.ssa.exception.ErrorResponse;
import com.doc.ssa.exception.GlobalException;
import com.doc.ssa.model.Appointment;
import com.doc.ssa.repo.AppointmentRepo;
import com.doc.ssa.request.AppointmentRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@OpenAPIDefinition(info=@Info(title="SSA - Appointment API"))
public class AppointmentController {
	
	
	@Autowired
	private AppointmentRepo appRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Operation(summary = "Add an appointment ")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Appointment Added",
					  content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = AppointmentDto.class)) } ),
			  @ApiResponse(responseCode = "404", description = "Appointment not added",
					  content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ErrorResponse.class)) } ),
			  @ApiResponse(responseCode = "400", description = "Appointment not added",
					  content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ErrorResponse.class)) } )
			  })
	@PostMapping("/save")
    public ResponseEntity<AppointmentDto> saveAppointment(@RequestBody AppointmentRequest req) throws Exception {
    	ResponseEntity<AppointmentDto> re = null;
    	Appointment appointment = new Appointment();
    	AppointmentDto appResp = new AppointmentDto();
    	
    	try {
    		 appointment = appRepo.save(modelMapper.map(req, Appointment.class));
    		 appResp = modelMapper.map(appointment, AppointmentDto.class);
    		re = new ResponseEntity<AppointmentDto>(appResp, HttpStatus.OK);
    	}catch (Exception e) {
			throw new GlobalException("Appointment not added. Error : "+e.getMessage());
		}
        return re;
    }
	
	@Operation(summary = "Get list of Appointments")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Appointments Data Found ", 
			    content = { @Content(mediaType = "application/json", 
			      array = @ArraySchema(schema = @Schema(implementation = ListAppointmentDto.class) ) ) } ),
			  @ApiResponse(responseCode = "404", description = "No Appointments Data found",
					  content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ErrorResponse.class)) } ),
			  @ApiResponse(responseCode = "400", description = "No Appointments Data found",
					  content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ErrorResponse.class)) } )
			  })
    @GetMapping("/list")
    public ResponseEntity<List<ListAppointmentDto>> list() {
    	ResponseEntity<List<ListAppointmentDto>> re = null;
    	List<ListAppointmentDto> appsDto = new ArrayList<>();
    	List<Map<String, String>> appsHm = null;
    	
    	//consDto = conRepo.findAll();
    	
    	appsHm = appRepo.findAllAppointments();
    	if( appsHm.size()>0 ) {
    		appsHm.stream()
	    		.map(x -> appsDto.add(modelMapper.map(x, ListAppointmentDto.class))).collect(Collectors.toList());
    		if( appsDto.size()>0 ) {
    			re = new ResponseEntity<List<ListAppointmentDto>>(appsDto, HttpStatus.OK);
    		}else {
    			throw new GlobalException("Data not found !");
    		}
    	}else {
    		throw new GlobalException("Data not found !");
    	}
    	
        return re;
    }
	
}