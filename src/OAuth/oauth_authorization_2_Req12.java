import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class oauth_authorization_2_Req12 {

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final java.io.File CREDENTIALS_FOLDER = new java.io.File("src/main/resources/credentials"); // Directory to store user credentials.

    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);

    private static Credential getCredentials() throws Exception {
        GoogleClientSecrets clientSecrets;
        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

        InputStream in = oauth_authorization_2_Req12.class.getResourceAsStream(CREDENTIALS_FOLDER + "/client_secret.json");
        clientSecrets = GoogleClientSecrets.load(jacksonFactory, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jacksonFactory,
                clientSecrets,
                SCOPES).setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER)).setAccessType("offline").build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws Exception {
        Credential credential = getCredentials();

        com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential).setApplicationName(APPLICATION_NAME).build();

        System.out.println("Authorization was successful!");
    }
}