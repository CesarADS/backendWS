package br.com.senac.api;

import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class TesteFilaConsumer {

    // Escutar fila. containerFactory é o canal de comunicação e deve ser igual ao método criado lá no WS emissor da mensagem
    @JmsListener(destination = "teste_fila", containerFactory = "defaultFactory")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem: " + mensagem);
    }

}
