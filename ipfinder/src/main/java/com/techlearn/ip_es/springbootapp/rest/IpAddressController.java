package com.techlearn.ip_es.springbootapp.rest;

import com.techlearn.ip_es.springbootapp.dao.IpAddressDto;
import com.techlearn.ip_es.springbootapp.exception.APIErrorResponse;
import com.techlearn.ip_es.springbootapp.exception.DataServicesException;
import com.techlearn.ip_es.springbootapp.model.IpAddress;
import com.techlearn.ip_es.springbootapp.services.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@RestController
@RequestMapping("/ipaddress")
@EnableAutoConfiguration
@Tag(name = "IPAddress", description = "Endpoints for IpAddress")
public class IpAddressController
{

	@Autowired
	private final IpAddressService ipAddressService;

	@Autowired
	public IpAddressController(IpAddressService ipAddressService)
	{
		this.ipAddressService = ipAddressService;
	}

	@Operation(summary = "Get IPAddress details for a ipValue", description = "Returns properties of the IPAddress for the requested value.")
	@Parameters(value = {
			@Parameter(name = "ipValue", description = "IP Address")
	})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returns IPAddress details",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = IpAddress.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthenticated",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "403", description = "Unauthorized",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "The requested finding does not exist",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class)))
	})
	@GetMapping("/{ipValue}")
	public ResponseEntity<List<IpAddress>> getIpAddress(@PathVariable("ipValue") String ipValue)
	{
		return ResponseEntity.ok(ipAddressService.getIpDetailsByValue(ipValue));
	}


	@Operation(summary = "Save the IP Address Details", description = "Save the IPAddress record with its details.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "This save the IPAddress record with fields supplied in request body.  " +
					"Supported Field(s):  " +
					"type" +
					"value" +
					"firstSeen" +
					"totalCount",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = IpAddress.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthenticated",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "403", description = "Unauthorized",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "The requested finding does not exist",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIErrorResponse.class)))
	})
	@PostMapping("/")
	public ResponseEntity<IpAddress> createRecord(@Valid  @RequestBody IpAddressDto ipAddressDto) throws ParseException, DataServicesException {
		return ResponseEntity.ok(ipAddressService.save(ipAddressDto));
	}
}
