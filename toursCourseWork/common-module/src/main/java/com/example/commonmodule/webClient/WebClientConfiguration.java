package com.example.commonmodule.webClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {
        private static final String BASE_URL = "https://search.tez-tour.com/tariffsearch/getResult";
        public static final int TIMEOUT =5000;
        @Bean
        public WebClient webClientWithTimeout() {
            final var tcpClient = TcpClient
                    .create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT*2)
                    .doOnConnected(connection -> {
                        connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT*2, TimeUnit.MILLISECONDS));
                        connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT*2, TimeUnit.MILLISECONDS));
                    });
            return WebClient.builder()
                    .baseUrl(BASE_URL)
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .build();
        }
}