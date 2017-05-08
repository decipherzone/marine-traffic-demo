/**
 * Created on 5/5/17 3:33 PM by Raja Dushyant Vashishtha
 * Sr. Software Engineer
 * rajad@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

package com.decipherzone.loginno.servlet;

import com.decipherzone.loginno.service.PortExpectedArrivalsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PortExpectedArrivalsService portExpectedArrivalsService = new PortExpectedArrivalsService();
        portExpectedArrivalsService.getExpectedArrivalsOnPort();
    }
}
