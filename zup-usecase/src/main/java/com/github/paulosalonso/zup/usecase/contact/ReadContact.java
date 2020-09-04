package com.github.paulosalonso.zup.usecase.contact;

import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.contact.ReadContactPort;
import com.github.paulosalonso.zup.usecase.port.customer.ReadCustomerPort;

import java.util.List;

public class ReadContact {

    private final ReadContactPort readContactPort;
    private final ReadCustomerPort readCustomerPort;

    public ReadContact(ReadContactPort readContactPort, ReadCustomerPort readCustomerPort) {
        this.readContactPort = readContactPort;
        this.readCustomerPort = readCustomerPort;
    }

    public Contact findById(Long customerId, Long contactId) {
        return readContactPort.findById(customerId, contactId).orElseThrow(NotFoundException::new);
    }

    public List<Contact> findAll(Long customerId) {
        if (!readCustomerPort.existsById(customerId)) {
            throw new NotFoundException();
        }

        return readContactPort.findAll(customerId);
    }
}
