package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import static com.example.myapplication.MainActivity.children1;
import static com.example.myapplication.MainActivity.children2;
import static com.example.myapplication.MainActivity.groups;
import static com.example.myapplication.MainActivity.listView;

public class TakeJson extends AsyncTask<Void, Void, String> {
    InfOfCompany infOfCompany;
    StringBuffer response;

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL("http://www.mocky.io/v2/56fa31e0110000f920a72134");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            JSONParser jsonParse = new JSONParser();
            JSONObject object = null;
            object = (JSONObject) jsonParse.parse(response.toString());
            JSONObject company = new JSONObject((Map) object.get("company"));
            Gson gson = new Gson();
            infOfCompany = gson.fromJson(String.valueOf(company), InfOfCompany.class);
            Collections.sort(infOfCompany.employees, infOfCompany.comparatorByName);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        adapter.add("Name: " + infOfCompany.getName());
        adapter.add("Age: " + infOfCompany.getAge());

        String[] parent = new String[]{"Competences", "Employees"};
        String[] childOfCompetences = new String[infOfCompany.getCompetences().size()];
        String[] childOfEmployees = new String[infOfCompany.getEmployees().size()];

        infOfCompany.getCompetences().toArray(childOfCompetences);

        for (int i = 0; i < infOfCompany.getEmployees().size(); i++) {
            childOfEmployees[i] = infOfCompany.getInfOfEmployees(i);
            children2.add(childOfEmployees[i]);
        }

        for (int i = 0; i < infOfCompany.getCompetences().size(); i++) {

            children1.add(childOfCompetences[i]);
        }
        groups.add(children1);
        groups.add(children2);
    }
}
