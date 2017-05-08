package com.decipherzone.loginno.service;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.List;


public final class HTTPService {
    /**
     * Executes the HTTP POST request
     *
     * @param url
     * @param postParams
     * @return
     * @throws Exception
     */
    public static Object sendPost(String url, List<NameValuePair> postParams) throws Exception {

        final HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(postParams));
        post.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpResponse httpResponse = client.execute(post);
        String s = EntityUtils.toString(httpResponse.getEntity());
        client.getConnectionManager().shutdown();
        return s;
    }

    /**
     * Executes the HTTP GET Request
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static Object sendGet(String url) throws Exception {
        final HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpResponse httpResponse = client.execute(get);
        client.getConnectionManager().shutdown();
        return EntityUtils.toString(httpResponse.getEntity());
    }

    /**
     * Gets the JSON ARRAY from given string
     *
     * @param object
     * @return
     */
    public static JSONArray getJSONArray(Object object) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray arrayObj = (JSONArray) jsonParser.parse(object.toString());
            return arrayObj;
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse given Object", e);
        }
    }

    /**
     * Gets the JSON OBJECT from the given string
     *
     * @param object
     * @return
     */
    public static JSONObject getJSONObject(Object object) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject arrayObj = (JSONObject) jsonParser.parse(object.toString());
            return arrayObj;
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse given Object", e);
        }
    }

    /**
     * Get bytes from the given URL
     *
     * @param urlString
     * @return
     */
    public static byte[] getBytesFromURL(String urlString) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            is = url.openStream();
            writeFromInputStreamToOutputStream(is, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteArrayOutputStream != null) byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (is != null) try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * Gets a file from the given URL
     *
     * @param urlString
     * @return
     * @throws FileNotFoundException
     */
    public static File getFileFromURL(String urlString) throws FileNotFoundException {
        String outputPath = (System.getProperty("java.io.tmpdir") + File.separator + Calendar.getInstance().getTime().getTime()) + ".png";
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            is = url.openStream();
            writeFromInputStreamToOutputStream(is, fileOutputStream);
            return new File(outputPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (is != null) try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * Write data from input stream to output stream
     *
     * @param is
     * @param os
     * @throws IOException
     */
    private static void writeFromInputStreamToOutputStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int n;
        while ((n = is.read(buffer)) > 0) {
            os.write(buffer, 0, n);
        }
    }

    /**
     * @Added - Abhishek
     * @desc - for download of sbc benefits pdf
     */
    public static InputStream getRawData(String url, List<NameValuePair> postParams) throws Exception {

        final HttpClient client = new DefaultHttpClient();
        HttpGet post = new HttpGet(url);
        post.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpResponse httpResponse = client.execute(post);
        InputStream inputStream = httpResponse.getEntity().getContent();
        client.getConnectionManager().shutdown();
        return inputStream;
    }
}
