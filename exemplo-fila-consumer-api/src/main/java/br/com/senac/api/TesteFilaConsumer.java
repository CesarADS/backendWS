package br.com.senac.api;

import br.com.senac.api.dtos.Produtos;
import com.google.gson.Gson;
import jakarta.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Gson gson;

    // Escutar fila. containerFactory é o canal de comunicação e deve ser igual ao método criado lá no WS emissor da mensagem
    @JmsListener(destination = "teste_fila", containerFactory = "defaultFactory")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem: " + mensagem);
    }

    @JmsListener(destination = "teste_fila_objeto", containerFactory = "defaultFactory")
    public void receberProduto(String mensagem){

        Produtos produto = gson.fromJson(mensagem, Produtos.class);

        System.out.println(produto.getId() + " | " + produto.getNome() + " | " + produto.getDescricao());

    }

}
