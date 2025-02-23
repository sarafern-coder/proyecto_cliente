package com.loeches.proyectogrupo3_tienda.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loeches.proyectogrupo3_tienda.R;
import com.loeches.proyectogrupo3_tienda.controladores.ProductAdapter;
import com.loeches.proyectogrupo3_tienda.modelos.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private ImageView img_Cart;
    private TextView txtCartCount;
    private int cartItemCount = 0;
    private Button btnGoToForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_products);
        img_Cart = findViewById(R.id.img_cart);
        txtCartCount = findViewById(R.id.txt_cart_count);
        btnGoToForm = findViewById(R.id.btn_go_to_form);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this::onAddToCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        btnGoToForm.setOnClickListener(v -> {
            if (cartItemCount == 0) {
                Toast.makeText(MainActivity.this, "Debe añadir al menos un producto al carrito.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, OrderFormActivity.class);
                intent.putExtra("num_paquetes", cartItemCount);

                // Obtén la fecha actual
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                intent.putExtra("fecha_compra", currentDate);

                startActivity(intent);
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        productList.add(new Product("Criadores, Pienso de Cordero y Arroz para perros adultos de raza mediana y grande.", 55.95, R.drawable.producto1, "Pienso con cordero y arroz especialmente formulado para cubrir las necesidades nutricionales de perros adultos de razas medianas y grandes. 15kg"));
        productList.add(new Product("True Origins Wild Puppy Country Water, Pienso de Pollo y Salmón para cachorros de perro.", 62.95, R.drawable.producto2, "Pienso para cachorros de todos los tamaños, sin cereales y con pollo que ayuda al crecimiento del perro. 12kg"));
        productList.add(new Product("True Origins Wild Adult Country Water, Pienso de Pollo y Salmón para gatos.", 42.95, R.drawable.producto3, "Pienso para gatos de edad adulta, sin gluten, con pollo y salmón. 6kg"));
        productList.add(new Product("Criadores, Pienso de pollo para gatos adultos esterilizados", 54.95, R.drawable.producto4, "Pienso para gatos esterilizados, con pollo fresco para que no cojan peso, con minerales para evitar infecciones urinarias o pérdida de vitalidad. 15kg"));
        productList.add(new Product("Sera Carnivore Professional, Comida para tortugas de agua", 6.29, R.drawable.producto5, "Alimento para reptiles carnívoros. 0.31kg"));
        productList.add(new Product("Sera Reptil Professional Herbivore Nature", 13.49, R.drawable.producto6, "Suplemento de alta calidad para reptiles herbívoros, como tortugas e iguanas, gránulos bicolor con anillo rico en fibra bruta y núcleo rico en vitaminas, óptima proporción calcio-fósforo 0.33kg"));
        productList.add(new Product("Versele-Laga Prestige, Pienso para periquitos", 4.39, R.drawable.producto7, "Alimento completo y nutritivo para periquitos. Mezcla de semillas de la mejor calidad. 1kg"));
        productList.add(new Product("Vitakraft Premium Menu, Comida para periquitos", 3.29, R.drawable.producto8, "Mixtura de semillas y granos para tus pájaros de la marca Vitakraft Premium Menu. 1kg"));
        productList.add(new Product("Home Friends Special Timothy, Heno", 5.99, R.drawable.producto9, "Alimento complementario de primera calidad para todo tipo de roedores y lagomorfos, ideal para cuidar el aparato digestivo y sus dientes. 0.5kg"));
        productList.add(new Product("Tetra Pond, sticks para peces de lago", 22.39, R.drawable.producto10, "Una alimentación completa, saludable y que potenciará sus colores naturales. 4kg"));
        productAdapter.updateProductList(productList);
    }

    private void onAddToCart(Product product) {
        cartItemCount++;
        txtCartCount.setText(String.valueOf(cartItemCount));
        Toast.makeText(this, product.getNombre() + " añadido al carrito.", Toast.LENGTH_SHORT).show();
    }
}
