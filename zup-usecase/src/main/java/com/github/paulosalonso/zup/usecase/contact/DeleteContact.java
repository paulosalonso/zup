package com.github.paulosalonso.zup.usecase.contact;

import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.contact.DeleteContactPort;
import com.github.paulosalonso.zup.usecase.port.contact.ReadContactPort;

public class DeleteContact {

    private final DeleteContactPort deleteContactPort;
    private final ReadContactPort readContactPort;

    public DeleteContact(DeleteContactPort deleteContactPort, ReadContactPort readContactPort) {
        this.deleteContactPort = deleteContactPort;
        this.readContactPort = readContactPort;
    }

    public void delete(Contact contact) {
        deleteContactPort.delete(contact);
    }

    public void deleteById(Long customerId, Long contactId) {
        if (!readContactPort.existsById(customerId, contactId)) {
            throw new NotFoundException();
        }

        deleteContactPort.deleteById(customerId, contactId);
    }
}
