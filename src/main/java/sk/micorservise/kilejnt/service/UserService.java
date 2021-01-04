package sk.micorservise.kilejnt.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.micorservise.kilejnt.Tokens;
import sk.micorservise.kilejnt.model.*;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private RestTemplate restTemplate;
    private Gson gson;

    private int perPage;
    private int pageInd;

    @Autowired
    public UserService() {
        restTemplate = new RestTemplate();
        gson = new Gson();
        perPage = 10;
        pageInd = 0;
    }


    public boolean logIn(String username,String password){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        LoginForm form = new LoginForm();
        form.setEmail(username);
        form.setPassword(password);

        String json_form = gson.toJson(form);
        System.out.println(json_form);

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);
        try {
            ResponseEntity<Void> rsp = restTemplate.exchange(Tokens.userUrl + "/login", HttpMethod.POST, entity, Void.class);
            if (!rsp.getStatusCode().equals(HttpStatus.OK)) {
                System.out.println(rsp.getStatusCode());
                return false;
            }


            String auth = Objects.requireNonNull(rsp.getHeaders().get(Tokens.HEADER_STRING)).get(0);
            //System.out.println(auth);
            Tokens.setUserToken(auth);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean registrate(String ime, String prezime, String email, String sifra, long pasos){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
        RegistrationForm form = new RegistrationForm();

        form.setIme(ime);
        form.setPrezime(prezime);
        form.setPasos(pasos);
        form.setEmail(email);
        form.setSifra(sifra);

        String json_form = gson.toJson(form);
        System.out.println(json_form);

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);
        ResponseEntity<String> rsp;
        try {
            rsp = restTemplate.exchange(Tokens.userUrl + "/register", HttpMethod.POST, entity, String.class);
        }catch (Exception e){
            return false;
        }
        if(!rsp.getStatusCode().equals(HttpStatus.ACCEPTED)) {
            System.out.println(rsp.getStatusCode());
            return false;
        }

        return true;
    }

    public UserInfo info(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);

        ResponseEntity<RegistrationForm> rsp;
        try {
            rsp = restTemplate.exchange(Tokens.userUrl + "/info", HttpMethod.GET, entity, RegistrationForm.class);
        }catch (Exception e){
            return null;
        }
        if(!rsp.hasBody() || rsp.getBody()==null)
            return null;

        RegistrationForm rf = rsp.getBody();
        UserInfo toRet = new UserInfo(rf);
        ResponseEntity<String> rsp1;
        try {
            rsp1 = restTemplate.exchange(Tokens.userUrl + "/getrank", HttpMethod.GET, entity, String.class);
        }catch (Exception e){
            return null;
        }
        if(!rsp1.hasBody() || rsp1.getBody()==null)
            return null;

        toRet.setRank(rsp1.getBody());
        return toRet;
    }

    public boolean updateInfo(String ime, String prezime, String email, String sifra, long pasos){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        RegistrationForm form = new RegistrationForm();
        form.setIme(ime);
        form.setPrezime(prezime);
        form.setPasos(pasos);
        form.setEmail(email);
        form.setSifra(sifra);

        String json_form = gson.toJson(form);
        System.out.println(json_form);

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);

        ResponseEntity<String> rsp = restTemplate.exchange(Tokens.userUrl+"/update",HttpMethod.PUT,entity,String.class);

        if(!rsp.getStatusCode().equals(HttpStatus.ACCEPTED) || !rsp.hasBody()) {
            System.out.println(rsp.getStatusCode());
            return false;
        }

        return true;
    }

    public List<KarticaForm> getCards(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());

        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);

        ResponseEntity<KarticaList> rsp = restTemplate.exchange(Tokens.userUrl+"/card/get",HttpMethod.GET,entity,KarticaList.class);

        if(!rsp.getStatusCode().equals(HttpStatus.ACCEPTED) || !rsp.hasBody()) {
            System.out.println(rsp.getStatusCode());
            return null;
        }
        if(rsp.getBody()==null)
            return null;

        return rsp.getBody().getList();
    }

    public boolean addCard(long brojKartice, String imeVlasnika, String prezimeVlasnika, int sigurnosniBroj){
        KarticaForm kartica = new KarticaForm();

        kartica.setBrojKartice(brojKartice);
        kartica.setImeVlasnika(imeVlasnika);
        kartica.setPrezimeVlasnika(prezimeVlasnika);
        kartica.setSigurnosniBroj(sigurnosniBroj);

        String json_form = gson.toJson(kartica);
        System.out.println(json_form);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);

        ResponseEntity<String> rsp = restTemplate.exchange(Tokens.userUrl+"/card/add",HttpMethod.POST,entity,String.class);

        if(!rsp.getStatusCode().equals(HttpStatus.OK) || !rsp.hasBody()) {
            System.out.println(rsp.getStatusCode());
            return false;
        }

        return true;

    }

    public List<Flight> getFlights(List<FlightCriteria> critrias,int pageInd){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());
        List<Flight> result = null;
        if(critrias.isEmpty()){

            HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
            ResponseEntity<FlightList> rsp = restTemplate.exchange(Tokens.flightUrl+"/flights/all/"+pageInd+"/"+perPage,HttpMethod.GET,entity,FlightList.class);
            if(!rsp.hasBody() || rsp.getBody()==null)
                return null;

            result = rsp.getBody().getList();
        }else{
            httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

            FlightCriteriaList fcl = new FlightCriteriaList(critrias);
            String json_form = gson.toJson(fcl);
            HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);
            ResponseEntity<FlightList> rsp = restTemplate.exchange(Tokens.flightUrl+"/flights/"+pageInd+"/"+perPage,HttpMethod.POST,entity,FlightList.class);
            if(!rsp.hasBody() || rsp.getBody()==null)
                return null;

            result = rsp.getBody().getList();
        }
        return result;
    }

    public boolean buyTicket(long fid,long cardId){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");

        BuyingForm element = new BuyingForm(fid,cardId);
        String json_form = gson.toJson(element);
        System.out.println(json_form);

        HttpEntity<String> entity = new HttpEntity<>(json_form,httpHeaders);

        ResponseEntity<Boolean> rsp = restTemplate.exchange(Tokens.ticketUrl+"/buy",HttpMethod.POST,entity,Boolean.class);

        if(!rsp.hasBody() || rsp.getBody()==null)
            return false;

        return rsp.getBody();
    }

    public List<Ticket> listTickets(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(Tokens.HEADER_STRING,Tokens.getUserToken());

        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);

        ResponseEntity<TicketList> rsp = restTemplate.exchange(Tokens.ticketUrl+"/list",HttpMethod.GET,entity,TicketList.class);

        if(!rsp.hasBody() || rsp.getBody()==null)
            return null;

        return rsp.getBody().getList();

    }

}
