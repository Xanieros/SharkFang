package com.revature.ws;

public class ArchiveWSProxy implements com.revature.ws.ArchiveWS {
  private String _endpoint = null;
  private com.revature.ws.ArchiveWS archiveWS = null;
  
  public ArchiveWSProxy() {
    _initArchiveWSProxy();
  }
  
  public ArchiveWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initArchiveWSProxy();
  }
  
  private void _initArchiveWSProxy() {
    try {
      archiveWS = (new com.revature.ws.ArchiveWSServiceLocator()).getArchiveWSPort();
      if (archiveWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)archiveWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)archiveWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (archiveWS != null)
      ((javax.xml.rpc.Stub)archiveWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.revature.ws.ArchiveWS getArchiveWS() {
    if (archiveWS == null)
      _initArchiveWSProxy();
    return archiveWS;
  }
  
  public void archive(int arg0) throws java.rmi.RemoteException{
    if (archiveWS == null)
      _initArchiveWSProxy();
    archiveWS.archive(arg0);
  }
  
  
}