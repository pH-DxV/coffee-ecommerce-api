package unitins.topicos1.Hash.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    // Sequencia aleatoria a ser adicionada na senha
    private String salt = "it##4r0f0d411";

    // Contagem de iteracoes
    private Integer iterationCount = 405;

    // Comprimento do hash em bits
    private Integer keyLength = 512;

    @Override
    public String getHashPassword(String password){

        try {

            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                            .generateSecret(
                                
                                new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterationCount, keyLength)
                            
                            ).getEncoded();

            return Base64.getEncoder().encodeToString(result);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){

            throw new RuntimeException(e);
            
        }

    }
    
    public static void main(String[]args){

        HashService hash = new HashServiceImpl();

        System.out.println(hash.getHashPassword("123"));
        System.out.println(hash.getHashPassword("123"));
        System.out.println(hash.getHashPassword("123"));
        
    }

}
