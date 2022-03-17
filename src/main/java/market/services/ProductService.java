package market.services;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;

import market.model.persistence.Product;

public class ProductService {

	private final Logger LOG = LogManager.getLogger(CategoryService.class);
	
	private EntityManager entityManager;
	
	public ProductService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void create(Product product) {
		if (product == null) {
			this.LOG.error("O Produto informado está nulo!");
			throw new RuntimeCryptoException("O Produto está nulo.");
		}
		
		entityManager.getTransaction().begin();
		this.product
		
	}
}
