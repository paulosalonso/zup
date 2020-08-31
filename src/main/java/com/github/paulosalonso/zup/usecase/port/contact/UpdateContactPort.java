package com.github.paulosalonso.zup.usecase.port.contact;

import com.github.paulosalonso.zup.domain.Contact;

public interface UpdateContactPort {
    Contact update(Contact contact);
}
