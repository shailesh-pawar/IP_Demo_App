package com.techlearn.ip_es.springbootapp.rest;

import com.techlearn.ip_es.springbootapp.dao.IpAddressDto;
import com.techlearn.ip_es.springbootapp.exception.DataServicesException;
import com.techlearn.ip_es.springbootapp.model.IpAddress;
import com.techlearn.ip_es.springbootapp.services.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/ipaddress")
@EnableAutoConfiguration
public class IpAddressController
{

	@Autowired
	private final IpAddressService ipAddressService;

	@Autowired
	public IpAddressController(IpAddressService ipAddressService)
	{
		this.ipAddressService = ipAddressService;
	}

	@GetMapping("/{ipValue}")
	public List<IpAddress> getIpAddress(@PathVariable("ipValue") String ipValue)
	{
		return ipAddressService.getIpDetailsByValue(ipValue);
	}

	@PostMapping("/")
	public IpAddress createRecord(@RequestBody IpAddressDto ipAddressDto) throws ParseException, DataServicesException {
		return ipAddressService.save(ipAddressDto);
	}
}
