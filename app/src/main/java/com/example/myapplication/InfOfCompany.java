package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InfOfCompany {
    String name;
    String age;
    List<String> competences = new ArrayList<>();

    public class employer {
        String name;
        String phone_number;
        List<String> skills = new ArrayList<>();

        public String getName() {
            return name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public List<String> getSkills() {
            return skills;
        }
    }

    List<employer> employees = new ArrayList<>();
    Comparator<employer> comparatorByName = new Comparator<employer>() {
        @Override
        public int compare(employer t1, employer t2) {
            return t1.name.compareTo(t2.name);
        }
    };

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    private void sortCompetences() {
        String lastComp=null;
        Collections.sort(competences);
        for (int i = 0; i < competences.size(); i++) {

            if (competences.get(i).contains(".")) {
                lastComp = competences.get(i);
                competences.remove(i);
            }
        }
        competences.add(lastComp);
    }

    public List<String> getCompetences() {
        sortCompetences();
        return competences;
    }

    public List<employer> getEmployees() {
        return employees;
    }

    public String getInfOfEmployees(int i) {

        String inf = "Name: " + employees.get(i).getName() + ", Phone number: " + employees.get(i).phone_number;
        String infOfSkills = " Skills: " + employees.get(i).getSkills().toString();
        return inf + "\n" + infOfSkills;
    }

}
