
package edu.bilkent.cs.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GameEngineService", targetNamespace = "http://risk.cs.bilkent.edu/", wsdlLocation = "http://localhost:8080/Risk/GameEngineService?wsdl")
public class GameEngineService
    extends Service
{

    private final static URL GAMEENGINESERVICE_WSDL_LOCATION;
    private final static WebServiceException GAMEENGINESERVICE_EXCEPTION;
    private final static QName GAMEENGINESERVICE_QNAME = new QName("http://risk.cs.bilkent.edu/", "GameEngineService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Risk/GameEngineService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GAMEENGINESERVICE_WSDL_LOCATION = url;
        GAMEENGINESERVICE_EXCEPTION = e;
    }

    public GameEngineService() {
        super(__getWsdlLocation(), GAMEENGINESERVICE_QNAME);
    }

    public GameEngineService(WebServiceFeature... features) {
        super(__getWsdlLocation(), GAMEENGINESERVICE_QNAME, features);
    }

    public GameEngineService(URL wsdlLocation) {
        super(wsdlLocation, GAMEENGINESERVICE_QNAME);
    }

    public GameEngineService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GAMEENGINESERVICE_QNAME, features);
    }

    public GameEngineService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GameEngineService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GameEngine
     */
    @WebEndpoint(name = "GameEnginePort")
    public GameEngine getGameEnginePort() {
        return super.getPort(new QName("http://risk.cs.bilkent.edu/", "GameEnginePort"), GameEngine.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GameEngine
     */
    @WebEndpoint(name = "GameEnginePort")
    public GameEngine getGameEnginePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://risk.cs.bilkent.edu/", "GameEnginePort"), GameEngine.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GAMEENGINESERVICE_EXCEPTION!= null) {
            throw GAMEENGINESERVICE_EXCEPTION;
        }
        return GAMEENGINESERVICE_WSDL_LOCATION;
    }

}
