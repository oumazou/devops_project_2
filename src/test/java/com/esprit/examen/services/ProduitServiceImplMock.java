package com.esprit.examen.services.produit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.ReglementRepository;
import com.esprit.examen.services.ProduitServiceImpl;
import com.esprit.examen.services.ReglementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplMock {
	
	@Mock
	ProduitRepository produitRepository;
	
	@InjectMocks
	ProduitServiceImpl produitService;
	
	Date date = new Date();
	Produit p = new Produit((long) 1, "ABC","AAA",200, date,date,null,null,null);
	
	List<Produit> lcp = new ArrayList<Produit>() {
		{
		add(new Produit((long) 2, "AB","CCC",300, date,date,null,null,null));
		add(new Produit((long) 3, "ABCD","DDD",400, date,date,null,null,null));
		}
	};

	
	@Test
	void testRetrieveAllProduits() {
		 
		Mockito.doReturn(lcp).when(produitRepository).findAll();
	    List<Produit> actualProducts = produitService.retrieveAllProduits();
	    assertThat(actualProducts).isEqualTo(lcp);
	}

	@Test
	void testAddProduit() {
        //CategorieProduit catP = categorieProduitService.addCategorieProduit(cp);
        //assertThat(catP).isNotNull();
		Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(p);
		Produit NewCP = produitService.addProduit(p);
		assertNotNull(NewCP);
		assertEquals(NewCP, p);

	}
	
	@Test
	void testDeleteProduit()  {
		produitService.deleteProduit((long) 1);;
		Mockito.verify(produitRepository, times(1)).deleteById((long) 1);
	}

	@Test
	void testUpdateCategorieProduit() {
		Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(p);
		p.setCodeProduit("code");
		Produit exisitingCP = produitService.updateProduit(p) ;
		
		assertNotNull(exisitingCP);
		assertEquals("code", p.getCodeProduit());
	}

	

	@Test
	void testRetrieveProduit() {
		Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Produit cp1 = produitService.retrieveProduit((long)1);
		Assertions.assertNotNull(cp1);
	}

}