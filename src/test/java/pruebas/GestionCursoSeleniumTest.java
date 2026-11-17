package pruebas;

import java.time.Duration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class GestionCursoSeleniumTest {

    private final String BASE_URL = "http://localhost:8080/Lab08IngSoftware";

    // 1. CASO DE PRUEBA: REGISTRAR NUEVO CURSO
    @Test
    public void test01_RegistrarCurso() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get(BASE_URL + "/CursoControlador?accion=listar");
            WebElement btnNuevo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("Nuevo Curso")));
            btnNuevo.click();

            WebElement txtCodigo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("codigo")));
            txtCodigo.sendKeys("IS-888");
            driver.findElement(By.name("nombre")).sendKeys("Pruebas Automatizadas");

            WebElement txtCreditos = driver.findElement(By.name("creditos"));
            txtCreditos.clear();
            txtCreditos.sendKeys("4");

            driver.findElement(By.name("docente")).sendKeys("Ing. Jhon H. Saldana Gatica");
            driver.findElement(By.name("btnRegistrar")).click();

            WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            assertTrue(tabla.getText().contains("IS-888"),
                    "El curso IS-888 debe aparecer registrado en la tabla");

            Thread.sleep(1500);
        } finally {
            driver.quit();
        }
    }

    // 2. CASO DE PRUEBA: LISTAR Y CONSULTAR CURSOS
    @Test
    public void test02_ListarCursos() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get(BASE_URL + "/CursoControlador?accion=listar");

            WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            assertTrue(tabla.isDisplayed(), "La tabla de cursos debe mostrarse en pantalla");
            assertTrue(tabla.getText().contains("IS-888"), "Debe listar el curso IS-888");

            Thread.sleep(1500);
        } finally {
            driver.quit();
        }
    }

    // 3. CASO DE PRUEBA: MODIFICAR CURSO EXISTENTE
    @Test
    public void test03_ModificarCurso() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get(BASE_URL + "/CursoControlador?accion=listar");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            // Ubicar la fila que contiene el curso IS-888 y hacer clic en su botón "Editar"
            WebElement btnEditar = driver.findElement(
                    By.xpath("//tr[contains(., 'IS-888')]//a[contains(text(), 'Editar')]"));
            btnEditar.click();

            // Esperar el formulario de edición y actualizar el nombre y créditos
            WebElement txtNombre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            txtNombre.clear();
            txtNombre.sendKeys("Pruebas Automatizadas Actualizado");

            WebElement txtCreditos = driver.findElement(By.name("creditos"));
            txtCreditos.clear();
            txtCreditos.sendKeys("5");

            // Hacer clic en el botón de guardar/actualizar (submit del formulario)
            driver.findElement(By.cssSelector("button[type='submit'], input[type='submit']")).click();

            // Validar en la tabla que el cambio se aplicó
            WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            assertTrue(tabla.getText().contains("Pruebas Automatizadas Actualizado"),
                    "El nombre del curso debe aparecer actualizado en la tabla");

            Thread.sleep(1500);
        } finally {
            driver.quit();
        }
    }

    // 4. CASO DE PRUEBA: ELIMINAR CURSO
    @Test
    public void test04_EliminarCurso() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get(BASE_URL + "/CursoControlador?accion=listar");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            // Buscar la fila del curso actualizado (o con IS-888) y hacer clic en Eliminar (sea <a> o <button>)
            WebElement btnEliminar = driver.findElement(
                    By.xpath("//tr[contains(., 'Pruebas Automatizadas') or contains(., 'IS-888')]//*[self::a or self::button][contains(text(), 'Eliminar')]"));
            btnEliminar.click();

            // Si hay confirm() de JavaScript, aceptarlo
            try {
                wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
            } catch (Exception e) {
                // No hubo alerta confirm(), continúa normal
            }

            // Verificar que el curso ya no figura en la tabla
            WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            assertFalse(tabla.getText().contains("Pruebas Automatizadas"),
                    "El curso debe haber sido eliminado del listado");

            Thread.sleep(1500);
        } finally {
            driver.quit();
        }
    }
}