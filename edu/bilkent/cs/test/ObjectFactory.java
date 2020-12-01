
package edu.bilkent.cs.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.bilkent.cs.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ActivatePLayer_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "activatePLayer");
    private final static QName _ActivatePLayerResponse_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "activatePLayerResponse");
    private final static QName _GetPLayers_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "getPLayers");
    private final static QName _GetPLayersResponse_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "getPLayersResponse");
    private final static QName _RegisterPLayer_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "registerPLayer");
    private final static QName _RegisterPLayerResponse_QNAME = new QName("http://simpleworldgame.cs.bilkent.edu/", "registerPLayerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.bilkent.cs.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActivatePLayer }
     * 
     */
    public ActivatePLayer createActivatePLayer() {
        return new ActivatePLayer();
    }

    /**
     * Create an instance of {@link ActivatePLayerResponse }
     * 
     */
    public ActivatePLayerResponse createActivatePLayerResponse() {
        return new ActivatePLayerResponse();
    }

    /**
     * Create an instance of {@link GetPLayers }
     * 
     */
    public GetPLayers createGetPLayers() {
        return new GetPLayers();
    }

    /**
     * Create an instance of {@link GetPLayersResponse }
     * 
     */
    public GetPLayersResponse createGetPLayersResponse() {
        return new GetPLayersResponse();
    }

    /**
     * Create an instance of {@link RegisterPLayer }
     * 
     */
    public RegisterPLayer createRegisterPLayer() {
        return new RegisterPLayer();
    }

    /**
     * Create an instance of {@link RegisterPLayerResponse }
     * 
     */
    public RegisterPLayerResponse createRegisterPLayerResponse() {
        return new RegisterPLayerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivatePLayer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActivatePLayer }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "activatePLayer")
    public JAXBElement<ActivatePLayer> createActivatePLayer(ActivatePLayer value) {
        return new JAXBElement<ActivatePLayer>(_ActivatePLayer_QNAME, ActivatePLayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivatePLayerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActivatePLayerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "activatePLayerResponse")
    public JAXBElement<ActivatePLayerResponse> createActivatePLayerResponse(ActivatePLayerResponse value) {
        return new JAXBElement<ActivatePLayerResponse>(_ActivatePLayerResponse_QNAME, ActivatePLayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPLayers }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPLayers }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "getPLayers")
    public JAXBElement<GetPLayers> createGetPLayers(GetPLayers value) {
        return new JAXBElement<GetPLayers>(_GetPLayers_QNAME, GetPLayers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPLayersResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPLayersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "getPLayersResponse")
    public JAXBElement<GetPLayersResponse> createGetPLayersResponse(GetPLayersResponse value) {
        return new JAXBElement<GetPLayersResponse>(_GetPLayersResponse_QNAME, GetPLayersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPLayer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterPLayer }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "registerPLayer")
    public JAXBElement<RegisterPLayer> createRegisterPLayer(RegisterPLayer value) {
        return new JAXBElement<RegisterPLayer>(_RegisterPLayer_QNAME, RegisterPLayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPLayerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterPLayerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://simpleworldgame.cs.bilkent.edu/", name = "registerPLayerResponse")
    public JAXBElement<RegisterPLayerResponse> createRegisterPLayerResponse(RegisterPLayerResponse value) {
        return new JAXBElement<RegisterPLayerResponse>(_RegisterPLayerResponse_QNAME, RegisterPLayerResponse.class, null, value);
    }

}
