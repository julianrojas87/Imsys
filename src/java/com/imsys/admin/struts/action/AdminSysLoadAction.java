/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Medidor;
import com.imsys.admin.dao.entity.Parametros;
import com.imsys.admin.dao.entity.Politica;
import com.imsys.admin.dao.entity.Rol;
import com.imsys.admin.dao.entity.TipoEvento;
import com.imsys.admin.dao.entity.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class AdminSysLoadAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private InputStream inputStream;

    public String manageAjaxRequest() {
        inputStream = new ByteArrayInputStream(Integer.toString((int) session.getAttribute("loading")).getBytes());
        return SUCCESS;
    }

    public String displayLoadInfo() {
        session.setAttribute("loading", 0);
        session.setAttribute("mainopt", "infoLoad");
        return SUCCESS;
    }

    public String loadInfo() throws IOException {
        session.setAttribute("mainopt", "loading");
        int loading = 0;

        AdminControl ac = new AdminControl();
        Parametros params = ac.getParams();
        String ipaddress = params.getLred() == 0 ? params.getVciplocal() : params.getVcipwan();
        File retrievedFile = null;
        
        try {
            retrievedFile = this.loadByTCP(retrievedFile, loading, params.getVcservlet(), ipaddress,
                params.getNptohttp(), params.getNptosocket());
        } catch (ConnectException ex) { 
            session.setAttribute("msj", "El tiempo de conexion ha sido excedido, intente de nuevo");
            session.setAttribute("loading", 200);
            session.setAttribute("mainopt", "infoLoad");
            return SUCCESS;
        }

        

        //retrievedFile = this.loadByHTTP(retrievedFile, loading, params.getVcservlet(), ipaddress, params.getNptohttp());
        if (retrievedFile != null) {
            this.processRetrievedFile(retrievedFile, loading, ac);

            session.setAttribute("loading", 100);
            session.setAttribute("msj", "Proceso completado!!");
            session.setAttribute("mainopt", "infoLoad");
            Usuario u = (Usuario) session.getAttribute("userObject");
            ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] actualizó la información desde el Sistema Central",
                    u.getVccoduser(), "Main/Procesos/CargaSysAdmin");
            return SUCCESS;
        } else {
            session.setAttribute("msj", "El puerto de conexión Socket no está disponible. Especifique un puerto diferente.");
            session.setAttribute("loading", 200);
            session.setAttribute("mainopt", "infoLoad");
            return SUCCESS;
        }

    }

    private File loadByTCP(File file, int loading, String servlet, String ip, int httpport, int socketport)
            throws MalformedURLException, IOException, SocketTimeoutException, ConnectException {
        URL url = new URL("http://" + ip + ":" + httpport + "/imsservlet01/" + servlet + "?opcion=22&type=tcp&port=" + socketport);
        //URL url = new URL("http://192.168.1.64:8084/AdminSysTest/AdminServlet?opt=tcp&port=" + socketport);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);

        if (urlConnection.getResponseMessage().equals("OK")) {
            file = new File("/Metrolink/retrieved.txt");
            Socket socket = new Socket(ip, socketport);
            //Socket socket = new Socket("192.168.1.64", socketport);
            InputStream is = socket.getInputStream();
            OutputStream output = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(output);
            byte[] bytes = new byte[4096];

            int count;

            while ((count = is.read(bytes)) > 0) {
                bos.write(bytes, 0, count);
                if (loading < 50) {
                    session.setAttribute("loading", loading++);
                } else {
                    session.setAttribute("loading", 50);
                }
            }
            bos.close();
            is.close();
            socket.close();
        }
        return file;
    }

    private File loadByHTTP(File file, int loading, String servlet, String ip, int httpport) {
        try {
            URL url = new URL("http://" + ip + ":" + httpport + "/imsservlet01/" + servlet + "?opcion=22&type=http");
            //URL url = new URL("http://localhost:8084/AdminSysTest/AdminServlet?opt=http");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            InputStream input = urlConnection.getInputStream();

            byte[] buffer = new byte[4096];
            file = new File("/Metrolink/retrieved.txt");
            int n = -1;
            OutputStream output = new FileOutputStream(file);

            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
                if (loading < 50) {
                    session.setAttribute("loading", loading++);
                } else {
                    session.setAttribute("loading", 50);
                }
            }

            output.close();
            urlConnection.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdminSysLoadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminSysLoadAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return file;
    }

    private void processRetrievedFile(File file, int loading, AdminControl ac) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        ArrayList<Usuario> users = new ArrayList();
        ArrayList<Rol> rolls = new ArrayList();
        ArrayList<Politica> politics = new ArrayList();
        ArrayList<TipoEvento> eventTypes = new ArrayList();
        ArrayList<Medidor> meters = new ArrayList();
        session.setAttribute("loading", 50);

        while ((line = reader.readLine()) != null) {
            if (loading < 100) {
                session.setAttribute("loading", loading++);
            } else {
                session.setAttribute("loading", 100);
            }
            if (line.split(";")[0].equals("User")) {
                Usuario u = new Usuario();
                u.setVccoduser(line.split(";")[1]);
                u.setVcnombre(line.split(";")[2]);
                u.setVcpass(line.split(";")[3]);
                u.setVcroll(line.split(";")[4]);
                u.setVcnit(line.split(";")[5]);
                u.setLactivo(line.split(";")[6].equals("Si") ? "true" : "false");
                users.add(u);
            }

            if (line.split(";")[0].equals("Roll")) {
                Rol r = new Rol();
                r.setNcodroll(Integer.parseInt(line.split(";")[1]));
                r.setVcdesroll(line.split(";")[2]);
                r.setLrlimusuario(line.split(";")[3].equals("Si") ? "true" : "false");
                r.setLroperario(line.split(";")[4].equals("Si") ? "true" : "false");
                rolls.add(r);
            }

            if (line.split(";")[0].equals("Politic")) {
                Politica p = new Politica();
                p.setVccodrutina(line.split(";")[1]);
                p.setLopcion(line.split(";")[2].equals("Si") ? "true" : "false");
                p.setLgrabar(line.split(";")[3].equals("Si") ? "true" : "false");
                p.setLborrar(line.split(";")[4].equals("Si") ? "true" : "false");
                p.setLimprimir(line.split(";")[5].equals("Si") ? "true" : "false");
                p.setLsimex(line.split(";")[6].equals("Si") ? "true" : "false");
                politics.add(p);
            }

            if (line.split(";")[0].equals("EventType")) {
                TipoEvento tp = new TipoEvento();
                tp.setNccodtipoeve(Integer.parseInt(line.split(";")[1]));
                tp.setVcdeseve(line.split(";")[2]);
                tp.setLaaplicacaja(line.split(";")[3].equals("Si") ? "true" : "false");
                tp.setLaaplicamed(line.split(";")[4].equals("Si") ? "true" : "false");
                eventTypes.add(tp);
            }

            if (line.split(";")[0].equals("Meter")) {
                Medidor m = new Medidor();
                m.setVcserie(line.split(";")[1]);
                m.setNdir(Integer.parseInt(line.split(";")[2]));
                m.setLestado(Integer.parseInt(line.split(";")[3]));
                meters.add(m);
            }
        }

        file.delete();
        ac.eraseTable("users");
        ac.eraseTable("rolls");
        ac.eraseTable("politics");
        ac.eraseTable("eventTypes");
        ac.eraseTable("meters");
        ac.reloadUsers(users);
        ac.reloadRolls(rolls);
        ac.reloadPolitics(politics);
        ac.reloadEventTypes(eventTypes);
        ac.reloadMeters(meters);
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
