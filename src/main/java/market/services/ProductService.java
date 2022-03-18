package market.services;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;

import market.model.dao.ProductDAO;
import market.model.persistence.Product;

public class ProductService {
    private static final Logger LOG = LogManager.getLogger(ProductService.class);
    private EntityManager entityManager;
    private ProductDAO productDAO;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.productDAO = new ProductDAO(entityManager);
    }

    public void create(Product product) {
        if (product == null) {
            this.LOG.error("O produto está nulo!");
            throw new RuntimeException("O produto está nulo!");
        }

        try {
            product.setName(product.getName().toLowerCase());
            product.setDescription(product.getDescription().toLowerCase());
            product.getCategory().setName(product.getCategory().getName().toLowerCase());

            beginTransaction();
            this.productDAO.create(product);
            commitAndCloseTransaction();
        } catch (Exception e) {
            this.LOG.error("Erro ao criar o produto, causado por: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
    
    public void delete(Long id) {
    	if(id == null) {
    		this.LOG.error("The ID is Null");
    		throw new RuntimeException("This ID is NULL");
    	}
    	
    	Product product = this.productDAO.getById(id);
    	
    }

    private void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    private void commitAndCloseTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}