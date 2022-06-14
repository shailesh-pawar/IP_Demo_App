package com.techlearn.ip_es.springbootapp.services;

import com.techlearn.ip_es.springbootapp.dao.IpAddressDto;
import com.techlearn.ip_es.springbootapp.exception.DataServicesErrorCode;
import com.techlearn.ip_es.springbootapp.exception.DataServicesException;
import com.techlearn.ip_es.springbootapp.model.IpAddress;
import com.techlearn.ip_es.springbootapp.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IpAddressService {

    private IpAddressRepository ipAddressRepository;

    @Autowired
    public IpAddressService(IpAddressRepository ipAddressRepository) {
        this.ipAddressRepository = ipAddressRepository;
    }

    public IpAddress save(IpAddressDto ipAddressDto) throws DataServicesException, ParseException {

        if (ipAddressDto.getTotalCount() < 8 || ipAddressDto.getTotalCount() > 100) {
            throw new DataServicesException("Invalid total count. Total count should be between 8 and 100", DataServicesErrorCode.INVALID_REQUEST);
        }

        if (ipAddressDto.getFirstSeen() == null) {
            throw new DataServicesException("Invalid Request: first seen date is missing from reuqest.", DataServicesErrorCode.INVALID_REQUEST);
        }

        Date firstSeenDate = getIsoFormatDate(ipAddressDto.getFirstSeen());

        IpAddress ipAddress = new IpAddress(ipAddressDto.getType(), ipAddressDto.getValue(), firstSeenDate, ipAddressDto.getTotalCount());

        ipAddressRepository.save(ipAddress);

        return ipAddress;
    }

    private Date getIsoFormatDate(String firstSeen) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return dateFormat.parse(firstSeen);

    }

    public List<IpAddress> getIpDetailsByValue(String value) {
        return ipAddressRepository.findByValue(value);
    }

}
