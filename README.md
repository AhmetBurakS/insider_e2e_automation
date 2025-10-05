# Insider QA Automation Task - E2E Test

Bu proje, [Insider](https://useinsider.com/) firması tarafından verilen teknik değerlendirme görevi kapsamında geliştirilmiştir. Projede belirtilen test senaryoları Selenium WebDriver, Java ve TestNG kullanılarak Page Object Model (POM) prensiplerine uygun şekilde otomatize edilmiştir.

## Görev Tanımı

Otomasyon senaryosu aşağıdaki adımları içermektedir:

1.  `https://useinsider.com/` adresi ziyaret edilir ve ana sayfanın açıldığı doğrulanır.
2.  Navigasyon çubuğundan "Company" menüsü ve ardından "Careers" seçeneği seçilir. Kariyer sayfasında "Locations", "Teams" ve "Life at Insider" bloklarının göründüğü kontrol edilir.
3.  `https://useinsider.com/careers/quality-assurance/` adresine gidilir, "See all QA jobs" butonuna tıklanır. İşler, Lokasyon: "Istanbul, Turkey" ve Departman: "Quality Assurance" olarak filtrelenir ve iş listesinin varlığı doğrulanır.
4.  Listelenen tüm işlerin Pozisyonunda “Kalite Güvencesi”, Departmanında “Kalite Güvencesi” ve Konumunda “İstanbul, Türkiye” yazdığı kontrol edilir.
5.  Herhangi bir ilanın "View Role" butonuna tıklanır ve sayfanın Lever başvuru formuna yönlendirdiği doğrulanır.

## Kullanılan Teknolojiler ve Yaklaşım

*   **Programlama Dili:** **Java 23**
*   **Otomasyon Aracı:** Selenium WebDriver (`4.29.0`)
*   **Test Çatısı (Framework):** TestNG (`7.10.2`)
*   **Build ve Bağımlılık Yönetimi:** Maven
*   **Raporlama:** **Allure Test Report** (`2.29.0`)
*   **Loglama:** Log4j
*   **Tasarım Deseni (Design Pattern):** Proje, istenildiği üzere **Page Object Model (POM)** tasarım desenine uygun olarak yapılandırılmıştır. Bu sayede testlerin bakımı ve okunabilirliği artırılmıştır.
*   **Selector'ler:** Elementlerin seçimi için optimize edilmiş ve dinamik durumlara karşı dayanıklı **XPath**'ler kullanılmıştır.
*   **Doğrulama (Assertion):** Test adımlarının sonuçlarını doğrulamak için TestNG `assert` yapıları kullanılmıştır.

## Kurulum ve Çalıştırma

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### Gereksinimler
*   **Java Development Kit (JDK) 23** veya üstü
*   Apache Maven (`3.8` veya üstü)
*   Google Chrome Tarayıcısı

### Adımlar

1.  **Projeyi Klonlayın:**
    ```bash
    git clone https://github.com/AhmetBurakS/ahmet_burak_sarigul_case.git
    ```

2.  **Proje Dizinine Gidin:**
    ```bash
    cd ahmet_burak_sarigul_case
    ```

3.  **Gerekli Bağımlılıkları Yükleyin:**
    Maven, `pom.xml` dosyasında belirtilen tüm bağımlılıkları otomatik olarak indirecektir.
    ```bash
    mvn clean install
    ```

4.  **Testleri Çalıştırın:**
    Aşağıdaki komut, Maven Surefire Plugin aracılığıyla projedeki tüm testleri çalıştıracaktır.
    ```bash
    mvn test
    ```
    Testler tamamlandığında sonuçlar konsolda görünecektir.

## Raporlama (Allure Report)

Test çalışması tamamlandıktan sonra detaylı bir Allure raporu oluşturmak için aşağıdaki adımları izleyebilirsiniz:

1.  **Testleri Çalıştırın (Rapor verileri için):**
    Yukarıdaki `mvn test` komutu çalıştırıldığında `allure-results` dizini otomatik olarak oluşur.

2.  **Allure Raporunu Oluşturun ve Görüntüleyin:**
    Aşağıdaki Maven komutu, yerel bir sunucu başlatarak Allure raporunu tarayıcınızda açacaktır.
    ```bash
    mvn allure:serve
    ```
Bu rapor, test adımlarını ve sonuçlarını detaylı bir şekilde incelemenizi sağlar.
