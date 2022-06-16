package com.techlearn.ip_es.springbootapp.services;

import com.techlearn.ip_es.springbootapp.dao.IpAddressDto;
import com.techlearn.ip_es.springbootapp.exception.DataServicesErrorCode;
import com.techlearn.ip_es.springbootapp.exception.DataServicesException;
import com.techlearn.ip_es.springbootapp.exception.NotfoundException;
import com.techlearn.ip_es.springbootapp.model.IpAddress;
import com.techlearn.ip_es.springbootapp.repository.IpAddressRepository;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.google.common.net.InetAddresses;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IpAddressService {

    private IpAddressRepository ipAddressRepository;

    @Autowired
    public IpAddressService(IpAddressRepository ipAddressRepository) {
        this.ipAddressRepository = ipAddressRepository;
    }

    /**
     *
     * @param ipAddressDto
     * @return
     * @throws DataServicesException
     * @throws ParseException
     */
    public IpAddress save(IpAddressDto ipAddressDto) throws DataServicesException, ParseException {

        if (ipAddressDto.getTotalCount() < 8 || ipAddressDto.getTotalCount() > 100) {
            log.error("Invalid totalCount. Total count should be between 8 and 100");
            throw new DataServicesException("Invalid total count. Total count should be between 8 and 100", DataServicesErrorCode.INVALID_REQUEST);
        }

        if (ipAddressDto.getFirstSeen() == null) {
            log.error("Invalid Request: first seen date is missing from request");
            throw new DataServicesException("Invalid Request: first seen date is missing from request.", DataServicesErrorCode.INVALID_REQUEST);
        }

        Date firstSeenDate = getIsoFormatDate(ipAddressDto.getFirstSeen());

        IpAddress ipAddress = new IpAddress(ipAddressDto.getType(), ipAddressDto.getValue(), firstSeenDate, ipAddressDto.getTotalCount());

        ipAddressRepository.save(ipAddress);

        return ipAddress;
    }

    /**
     * Convert date to ISO format
     * @param firstSeen
     * @return
     * @throws ParseException
     */
    private Date getIsoFormatDate(String firstSeen) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.parse(firstSeen);
    }

    /**
     *
     * @param value
     * @return
     * @throws DataServicesException
     */
    public List<IpAddress> getIpDetailsByValue(String value) throws DataServicesException {

        if(isValidIpAddress(value)) {
            log.error("Invalid IPAddress value");
            throw new DataServicesException("Invalid IPAddress value", DataServicesErrorCode.INVALID_REQUEST);
        }

        List<IpAddress> ipAddresses = ipAddressRepository.findByValue(value);

        if(CollectionUtils.isEmpty(ipAddresses)) {
            log.error("Record not found");
            throw new NotfoundException();
        }

        return ipAddresses;
    }

    /**
     * This checks whether value passed is correct IP Address or not
     * @param ipAddress
     * @return
     */
    private boolean isValidIpAddress(String ipAddress)
    {
        return InetAddresses.isInetAddress(ipAddress);
    }

}
