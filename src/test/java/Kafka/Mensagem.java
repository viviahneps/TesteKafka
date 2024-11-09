package Kafka;

import DTO.Pessoa;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import java.util.Locale;
import java.util.Random;

public class Mensagem {


    public JSONObject geraMensagem(){
        Faker fake = new Faker(new Locale("PT-BR"));
        JSONObject json = new JSONObject();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(fake.name().firstName() +" "+fake.name().firstName());
        pessoa.setCPF(gerarCPF());
        pessoa.setLogradouro(fake.address().fullAddress());

        json.put("nome", pessoa.getNome());
        json.put("cpf",pessoa.getCPF());
        json.put("Logradouro",pessoa.getLogradouro());
        return json;
    }

    public  String gerarCPF() {
        Random random = new Random();
        int[] cpf = new int[11];

        // Gera os 9 primeiros dígitos do CPF
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcula o primeiro dígito verificador
        cpf[9] = calcularDigitoVerificador(cpf, 10);

        // Calcula o segundo dígito verificador
        cpf[10] = calcularDigitoVerificador(cpf, 11);

        // Concatena os dígitos para formar o CPF
        StringBuilder cpfFormatado = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpfFormatado.append(cpf[i]);
            if (i == 2 || i == 5) {
                cpfFormatado.append(".");
            } else if (i == 8) {
                cpfFormatado.append("-");
            }
        }

        return cpfFormatado.toString();
    }


    private  int calcularDigitoVerificador(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}


