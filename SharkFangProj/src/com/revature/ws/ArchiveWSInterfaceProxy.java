package com.revature.ws;

public class ArchiveWSInterfaceProxy implements com.revature.ws.ArchiveWSInterface {
  private String _endpoint = null;
  private com.revature.ws.ArchiveWSInterface archiveWSInterface = null;
  
  public ArchiveWSInterfaceProxy() {
    _initArchiveWSInterfaceProxy();
  }
  
  public ArchiveWSInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initArchiveWSInterfaceProxy();
  }
  
  private void _initArchiveWSInterfaceProxy() {
    try {
      archiveWSInterface = (new com.revature.ws.ArchiveWSServiceLocator()).getArchiveWSPort();
      if (archiveWSInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)archiveWSInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)archiveWSInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (archiveWSInterface != null)
      ((javax.xml.rpc.Stub)archiveWSInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.revature.ws.ArchiveWSInterface getArchiveWSInterface() {
    if (archiveWSInterface == null)
      _initArchiveWSInterfaceProxy();
    return archiveWSInterface;
  }
  
  public void archive(int arg0) throws java.rmi.RemoteException{
    if (archiveWSInterface == null)
      _initArchiveWSInterfaceProxy();
    archiveWSInterface.archive(arg0);
  }
  
  
}