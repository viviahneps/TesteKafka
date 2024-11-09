package step;

import Kafka.Mensagem;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.json.JSONObject;
import org.junit.Assert;
import Kafka.Cnsm_Teste;
import Kafka.Prdc_Teste;

public class Pessoa_Step {

  private Prdc_Teste prod = new Prdc_Teste();
  private Cnsm_Teste cons = new Cnsm_Teste();
  private Mensagem msg = new Mensagem();
  private  JSONObject entrada;
  private String retorno;

    @Dado("^produzo mensagem com dados da pessoa no \"([^\"]*)\"$")
    public void produzo_mensagem_com_dados_da_pessoa_no(String topicProd) throws Throwable {
         entrada = msg.geraMensagem();
     //   prod.produzindoTopico(entrada, topicProd);
    }

    @Quando("^consumo a mensagem  no \"([^\"]*)\"$")
     public void consumo_a_mensagem_no(String topicoCons) throws Throwable {
      //retorno = cons.consumindoTopico(topicoCons);
    }

    @Então("^dados da pessoa foram gravados com sucesso$")
    public void dados_da_pessoa_foram_gravados_com_sucesso() throws Throwable {
      Assert.assertEquals(entrada.toString(),retorno);
    }

}
