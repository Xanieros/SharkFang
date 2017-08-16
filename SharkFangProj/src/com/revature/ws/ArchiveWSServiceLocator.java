/**
 * ArchiveWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.revature.ws;

public class ArchiveWSServiceLocator extends org.apache.axis.client.Service implements com.revature.ws.ArchiveWSService {

    public ArchiveWSServiceLocator() {
    }


    public ArchiveWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ArchiveWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ArchiveWSPort
    private java.lang.String ArchiveWSPort_address = "http://ec2-184-73-28-116.compute-1.amazonaws.com:8080/BattleshipArchive";

    public java.lang.String getArchiveWSPortAddress() {
        return ArchiveWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ArchiveWSPortWSDDServiceName = "ArchiveWSPort";

    public java.lang.String getArchiveWSPortWSDDServiceName() {
        return ArchiveWSPortWSDDServiceName;
    }

    public void setArchiveWSPortWSDDServiceName(java.lang.String name) {
        ArchiveWSPortWSDDServiceName = name;
    }

    public com.revature.ws.ArchiveWS getArchiveWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ArchiveWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getArchiveWSPort(endpoint);
    }

    public com.revature.ws.ArchiveWS getArchiveWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.revature.ws.ArchiveWSPortBindingStub _stub = new com.revature.ws.ArchiveWSPortBindingStub(portAddress, this);
            _stub.setPortName(getArchiveWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setArchiveWSPortEndpointAddress(java.lang.String address) {
        ArchiveWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.revature.ws.ArchiveWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.revature.ws.ArchiveWSPortBindingStub _stub = new com.revature.ws.ArchiveWSPortBindingStub(new java.net.URL(ArchiveWSPort_address), this);
                _stub.setPortName(getArchiveWSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ArchiveWSPort".equals(inputPortName)) {
            return getArchiveWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.revature.com/", "ArchiveWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.revature.com/", "ArchiveWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ArchiveWSPort".equals(portName)) {
            setArchiveWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
