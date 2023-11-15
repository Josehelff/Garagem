package garagem;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
 
 
public class Pricipal {
 
    public static void main(String[] args) {
    	 // Cria uma fábrica de gerenciadores de entidade para a unidade de persistência chamada 'garagem'.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("garagem");
        // Cria um gerenciador de entidade a partir da fábrica.
        EntityManager em = emf.createEntityManager();

        // Cria três instâncias da classe clientes com diferentes valores.
        Clientes c1 = new Clientes(null, "Lucas", "21 99988786902");
        Clientes c2 = new Clientes(null, "Joao", "21 99988786903");
        Clientes c3 = new Clientes(null, "Pedro", "21 99988786901");
 
        carros carro1 = new carros("ABC1234", "Chevrolet", "Onix", 2020);
        carros carro2 = new carros("XYZ5678", "Ford", "Focus", 2019);
        carros carro3 = new carros("ERT5678", "GM", "Astra", 2008);
 
        c1.getCarros().add(carro1);
        c2.getCarros().add(carro2);
        c3.getCarros().add(carro3);
 
        Clientes c;
 
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
 
       
 
        // Inicia uma transação para realizar operações de persistência.
        em.getTransaction().begin();
 
        try {
            // Persiste as novas instâncias de Cliente e carros no banco de dados.
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(carro1);
            em.persist(carro2);
            em.persist(carro3);
            
 
            // Busca uma entidade Cliente com o ID 2 no banco de dados e armazena em 'c'.
            c = em.find(Clientes.class, 2);
            // Imprime a representação em string da entidade Cliente encontrada.
            System.out.println(c);
         // Verifica se a entidade foi encontrada antes de tentar acessar seus métodos.
            if (c != null) {
                // Imprime a representação em string da entidade Cliente encontrada.
                System.out.println(c);
                // Altera o nome da entidade Cliente encontrada para "Robson".
                c.setNome("Robson");
            }
            // Faz um update nos dados do usuário id 2 para o nome Robson.
            	em.merge(c);
            	System.out.println(c);
            	//remove os dados do usuario com id 1
            	c1 = em.find(Clientes.class, 1);
                System.out.println(c1);
                em.remove(c1);
 
            // Finaliza a transação, aplicando as operações realizadas.
            em.getTransaction().commit();
 
            // Busca novamente a entidade Cliente com o ID 2 no banco de dados.
            c = em.find(Clientes.class, 2);
            // Imprime a representação em string da entidade Cliente encontrada.
            System.out.println(c);
 
            // Exibe os clientes após a transação
            System.out.println("Clientes após a transação:");
            System.out.println(c1 + "foi excluido da tabela");
            System.out.println(c2);
            System.out.println(c3);
 
            // Busca um cliente pelo ID
             Clientes clienteRecuperado = em.find(Clientes.class, c2.getId());
            System.out.println("Cliente recuperado: " + clienteRecuperado);
 
            // Exibe os carros associados ao cliente recuperado
             List<carros> carrosDoClienteRecuperado = clienteRecuperado.getCarros();
            System.out.println("Carros do cliente recuperado: " + carrosDoClienteRecuperado);
        } catch (Exception e) {
            // Se houver algum erro, faz rollback na transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
             // Finaliza a transação, aplicando as operações realizadas.
                
            }
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            em.close();
        }
        }
    }