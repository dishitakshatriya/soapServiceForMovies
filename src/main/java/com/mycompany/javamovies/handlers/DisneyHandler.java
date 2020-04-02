/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamovies.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.w3c.dom.NodeList;

/**
 *
 * @author dishi
 */
public class DisneyHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return new HashSet<QName>();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            SOAPMessage message = context.getMessage();
            message.writeTo(System.out);
                Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
                if (!outbound) {
                    NodeList childrenList = message.getSOAPBody().getFirstChild().getChildNodes();
                    if (message.getSOAPBody().getFirstChild().getLocalName().equals("addContent") ||
                            (message.getSOAPBody().getFirstChild().getLocalName().equals("updateContent"))) {
                        for (int i = 0; i < childrenList.getLength(); i++) {
                            if (childrenList.item(i).getTextContent().contains("disney")) {
                                SOAPBody soapBody = message.getSOAPPart().getEnvelope().getBody();
                                SOAPFault soapFault = soapBody.addFault();
                                soapFault.setFaultString("Not permitted! Cannot add a show that contains 'Disney' in any fields");
                                throw new SOAPFaultException(soapFault);
                            }
                        }
                    }
                }
        } catch (IOException ex) {
            Logger.getLogger(DisneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SOAPException ex) {
            Logger.getLogger(DisneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}
