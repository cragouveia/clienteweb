package br.com.quantati.clienteweb.conf;

import org.hibernate.type.ByteType;

/**
 * Created by carlos on 06/06/2017.
 */
public class CustomByteType extends ByteType {

    @Override
    public String getName() {
        return "bytea";
    }
}
