/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Medidor;
import com.imsys.admin.dao.entity.Parametros;
import com.imsys.admin.dao.entity.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class ArchivosAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private String btnopt;
    private String serie;
    private String dir;
    private String nitempresa;
    private String codsubest;
    private String codcto;
    private String codtrans;
    private String codcaja;
    private String diriploc;
    private String diripwan;
    private String ptohttp;
    private String ptosocket;
    private String freclec;
    private String deltaman;
    private String ptoserial;
    private String velocidad;
    private String bitsdatos;
    private String paridad;
    private String bitsparada;
    private String codific;
    private String latitud;
    private String longitud;
    private String servlet;
    private String nettype;
    private List<String> encodes = Arrays.asList("rtu", "ascii");
    private List<String> speeds = Arrays.asList("1200", "2400", "4800", "9600", "14400", "19200", "28800", "33600");
    private List<String> databits = Arrays.asList("5", "7", "8");
    private List<String> stopbits = Arrays.asList("1", "2");
    private List<String> parity = Arrays.asList("None", "Odd", "Even");
    private List<String> network = Arrays.asList("LAN", "WAN");

    public String displaySetup() {
        AdminControl ac = new AdminControl();
        Parametros params = ac.getParams();
        this.setNitempresa(params.getVcnit());
        this.setCodsubest(params.getVccodsubest());
        this.setCodcto(params.getVccodcto());
        this.setCodtrans(params.getVccodtrans());
        this.setCodcaja(params.getVccodcaja());
        this.setDiriploc(params.getVciplocal());
        this.setDiripwan(params.getVcipwan());
        this.setPtohttp(Integer.toString(params.getNptohttp()));
        this.setPtosocket(Integer.toString(params.getNptosocket()));
        this.setFreclec(Integer.toString(params.getNfrecdescarga()));
        this.setDeltaman(params.getVcdeltainicmmto());
        this.setPtoserial(params.getVcpuerto());
        this.setVelocidad(Integer.toString(params.getNvelocidad()));
        this.setBitsdatos(Integer.toString(params.getNdatabits()));
        this.setParidad(params.getVcparidad());
        this.setBitsparada(Integer.toString(params.getNbitsparada()));
        this.setCodific(params.getVccodific());
        this.setLatitud(Float.toString(params.getNlatitud()));
        this.setLongitud(Float.toString(params.getNlongitud()));
        this.setServlet(params.getVcservlet());
        if (params.getLred() == 0) {
            this.setNettype("LAN");
        } else {
            this.setNettype("WAN");
        }

        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] consultó los parámetros "
                + "de configuración de la caja.", u.getVccoduser(), "Main/Archivos/Medidores");
        session.setAttribute("mainopt", "setup");
        return SUCCESS;
    }

    public String updateSetup() {
        AdminControl ac = new AdminControl();
        Parametros params = ac.getParams();
        params.setVcnit(this.getNitempresa());
        params.setVccodsubest(this.getCodsubest());
        params.setVccodcto(this.getCodcto());
        params.setVccodtrans(this.getCodtrans());
        params.setVccodcaja(this.getCodcaja());
        params.setVciplocal(this.getDiriploc());
        params.setVcipwan(this.getDiripwan());
        params.setVcdeltainicmmto(this.getDeltaman());
        params.setVcpuerto(this.getPtoserial());
        params.setVcparidad(this.getParidad());
        params.setVccodific(this.getCodific());
        params.setVcservlet(this.getServlet());
        params.setLred(this.getNettype().equals("LAN") ? 0 : 1);

        int i = 0;
        try {
            params.setNptohttp(Integer.parseInt(this.getPtohttp()));
            i++;
            params.setNptosocket(Integer.parseInt(this.getPtosocket()));
            i++;
            params.setNfrecdescarga(Integer.parseInt(this.getFreclec()));
            i++;
            params.setNvelocidad(Integer.parseInt(this.getVelocidad()));
            i++;
            params.setNdatabits(Integer.parseInt(this.getBitsdatos()));
            i++;
            params.setNbitsparada(Integer.parseInt(this.getBitsparada()));
            i++;
            params.setNlatitud(Float.parseFloat((this.getLatitud())));
            i++;
            params.setNlongitud(Float.parseFloat(this.getLongitud()));
        } catch (NumberFormatException e) {
            if (i == 0) {
                session.setAttribute("msj", "El Parámetro Puerto HTTP solo acepta valores numéricos");
            }
            if (i == 1) {
                session.setAttribute("msj", "El Parámetro Puerto Socket solo acepta valores numéricos");
            }
            if (i == 2) {
                session.setAttribute("msj", "El Parámetro Frecuencia de Lectura solo acepta valores numéricos");
            }
            if (i == 3) {
                session.setAttribute("msj", "El Parámetro Velocidad solo acepta valores numéricos");
            }
            if (i == 4) {
                session.setAttribute("msj", "El Parámetro Bits de Datos solo acepta valores numéricos");
            }
            if (i == 5) {
                session.setAttribute("msj", "El Parámetro Bits de Parada solo acepta valores numéricos");
            }
            if (i == 6) {
                session.setAttribute("msj", "El Parámetro Latitud solo acepta valores numéricos");
            }
            if (i == 7) {
                session.setAttribute("msj", "El Parámetro Longitud solo acepta valores numéricos");
            }
            session.setAttribute("mainopt", "setup");
            return SUCCESS;
        }

        ac.eraseTable("params");
        ac.insertParams(params);

        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] actualizó los parámetros "
                + "de configuración de la caja.", u.getVccoduser(), "Main/Archivos/Medidores");

        session.setAttribute("mainopt", "setup");
        session.setAttribute("msj", "Parámetros actualizados de forma exitosa");
        return SUCCESS;
    }

    public String displayMeters() {
        AdminControl ac = new AdminControl();
        ArrayList<Medidor> lecs = ac.getMedidores();
        List<Medidor> displecs = new ArrayList();
        if (lecs.size() > 10) {
            displecs = lecs.subList(0, 10);
        } else {
            for (Medidor m : lecs) {
                displecs.add(m);
            }
        }
        session.setAttribute("medidores", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", 1);
        session.setAttribute("mainopt", "medidores");
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] consultó los Medidores.",
                u.getVccoduser(), "Main/Archivos/Medidores");
        return SUCCESS;
    }

    public String displayMeterN() {
        AdminControl ac = new AdminControl();
        ArrayList<Medidor> lecs = ac.getMedidores();
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Medidor> displecs = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = (opt * 10) - 10;
            for (int i = lim; i < lim + 11; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
            if (lecs.size() > 10) {
                displecs = lecs.subList(0, 10);
            } else {
                for (Medidor l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("medidores", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "medidores");
        return SUCCESS;
    }

    public String searchMeters() {
        List<Medidor> te = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;

        if (this.getSerie().length() > 0) {
            inputs = true;
            ArrayList<Medidor> t = ac.getMedidorbySerie(this.getSerie());
            if (t.size() > 0) {
                for (Medidor m : t) {
                    te.add(m);
                }
            }
        }

        if (this.getDir().length() > 0) {
            inputs = true;
            if (te.size() > 0) {
                ArrayList<Medidor> t = new ArrayList();
                try {
                    for (Medidor m : te) {
                        if (m.getNdir() == Integer.parseInt(this.getDir())) {
                            t.add(m);
                        }
                    }
                    te = t;
                } catch (NumberFormatException e) {
                    session.setAttribute("mainopt", "medidores");
                    session.setAttribute("msj", "El Parámetro de busqueda Dirección solo acepta valores numéricos");
                    return SUCCESS;
                }

            } else {
                try {
                    ArrayList<Medidor> t = ac.getMedidorbyDir(Integer.parseInt(this.getDir()));
                    if (t.size() > 0) {
                        for (Medidor m : t) {
                            te.add(m);
                        }
                    }
                } catch (NumberFormatException e) {
                    session.setAttribute("mainopt", "medidores");
                    session.setAttribute("msj", "El Parámetro de busqueda Dirección solo acepta valores numéricos");
                    return SUCCESS;
                }
            }
        }

        if (!inputs) {
            session.setAttribute("mainopt", "medidores");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        } else {
            if (te.size() > 0) {
                if (te.size() > 10) {
                    session.setAttribute("medidores", te.subList(0, 10));
                } else {
                    List<Medidor> temp = new ArrayList();
                    for (Medidor l : te) {
                        temp.add(l);
                    }
                    session.setAttribute("medidores", temp);
                }
                session.setAttribute("searchmedidores", te);
                session.setAttribute("totalevec", te.size());
                session.setAttribute("actualevec", 1);
                session.setAttribute("mainopt", "searchMeters");
                return SUCCESS;
            } else {
                session.setAttribute("mainopt", "medidores");
                session.setAttribute("msj", "No existen resultados para esta busqueda");
                return SUCCESS;
            }
        }
    }

    public String displaySearchMeterN() {
        ArrayList<Medidor> lecs = (ArrayList<Medidor>) session.getAttribute("searchmedidores");
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Medidor> displecs = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = (opt*10) - 10;
            for (int i = lim; i < lim + 11; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
            if (lecs.size() > 10) {
                displecs = lecs.subList(0, 10);
            } else {
                for (Medidor l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("medidores", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "searchMeters");
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }

    public String getBtnopt() {
        return btnopt;
    }

    public void setBtnopt(String btnopt) {
        this.btnopt = btnopt;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getNitempresa() {
        return nitempresa;
    }

    public void setNitempresa(String nitempresa) {
        this.nitempresa = nitempresa;
    }

    public String getCodsubest() {
        return codsubest;
    }

    public void setCodsubest(String codsubest) {
        this.codsubest = codsubest;
    }

    public String getCodcto() {
        return codcto;
    }

    public void setCodcto(String codcto) {
        this.codcto = codcto;
    }

    public String getCodtrans() {
        return codtrans;
    }

    public void setCodtrans(String codtrans) {
        this.codtrans = codtrans;
    }

    public String getCodcaja() {
        return codcaja;
    }

    public void setCodcaja(String codcaja) {
        this.codcaja = codcaja;
    }

    public String getDiriploc() {
        return diriploc;
    }

    public void setDiriploc(String diriploc) {
        this.diriploc = diriploc;
    }

    public String getDiripwan() {
        return diripwan;
    }

    public void setDiripwan(String diripwan) {
        this.diripwan = diripwan;
    }

    public String getPtohttp() {
        return ptohttp;
    }

    public void setPtohttp(String ptohttp) {
        this.ptohttp = ptohttp;
    }

    public String getPtosocket() {
        return ptosocket;
    }

    public void setPtosocket(String ptosocket) {
        this.ptosocket = ptosocket;
    }

    public String getFreclec() {
        return freclec;
    }

    public void setFreclec(String freclec) {
        this.freclec = freclec;
    }

    public String getDeltaman() {
        return deltaman;
    }

    public void setDeltaman(String deltaman) {
        this.deltaman = deltaman;
    }

    public String getPtoserial() {
        return ptoserial;
    }

    public void setPtoserial(String ptoserial) {
        this.ptoserial = ptoserial;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getBitsdatos() {
        return bitsdatos;
    }

    public void setBitsdatos(String bitsdatos) {
        this.bitsdatos = bitsdatos;
    }

    public String getParidad() {
        return paridad;
    }

    public void setParidad(String paridad) {
        this.paridad = paridad;
    }

    public String getBitsparada() {
        return bitsparada;
    }

    public void setBitsparada(String bitsparada) {
        this.bitsparada = bitsparada;
    }

    public String getCodific() {
        return codific;
    }

    public void setCodific(String codific) {
        this.codific = codific;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    public List<String> getSpeeds() {
        return speeds;
    }

    public void setSpeeds(List<String> speeds) {
        this.speeds = speeds;
    }

    public List<String> getDatabits() {
        return databits;
    }

    public void setDatabits(List<String> databits) {
        this.databits = databits;
    }

    public List<String> getStopbits() {
        return stopbits;
    }

    public void setStopbits(List<String> stopbits) {
        this.stopbits = stopbits;
    }

    public List<String> getParity() {
        return parity;
    }

    public List<String> getEncodes() {
        return encodes;
    }

    public void setEncodes(List<String> encodes) {
        this.encodes = encodes;
    }

    public void setParity(List<String> parity) {
        this.parity = parity;
    }

    public String getServlet() {
        return servlet;
    }

    public void setServlet(String servlet) {
        this.servlet = servlet;
    }

    public List<String> getNetwork() {
        return network;
    }

    public void setNetwork(List<String> network) {
        this.network = network;
    }

    public String getNettype() {
        return nettype;
    }

    public void setNettype(String nettype) {
        this.nettype = nettype;
    }
}
