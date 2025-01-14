# Tarator 📸

**Tarator**, kullanıcıların fotoğraflarını düzenleyebileceği, modern ve kullanıcı dostu bir mobil uygulamadır. Jetpack Compose ve Kotlin kullanılarak geliştirilen uygulama, fotoğraf düzenleme araçlarıyla etkili bir deneyim sunar.

## 🚀 Özellikler

- **Filtreleme**: Fotoğraflarınıza çeşitli filtreler uygulayın.
- **Araçlar**:
    - Kırpma (1x1, 16x9, 4x3 oran seçenekleri)
    - Parlaklık ve kontrast ayarları
- **Fırça Modu**:
    - Fotoğraf üzerinde çizim yapma
    - Fırça ve silgi seçenekleri
    - Çizim renkleri seçimi
- **Koyu ve Açık Tema**: Uygulama lacivert ve turuncu renk temalarını kullanır.
- **Görselleri Kaydetme**: Düzenlenmiş fotoğrafları galerinizde saklayın.

## 🛠️ Teknolojiler ve Araçlar

- **Kotlin**: Uygulamanın geliştirilmesi için kullanılan dil.
- **Jetpack Compose**: UI tasarımı için modern bir araç.
- **ViewModel**: Veri yönetimi ve durum takibi.
- **Canvas API**: Çizim özellikleri için.



## 📸 Ekran Görüntüleri

### Ana Sayfa
![Ana Sayfa](drawable/home.png)

### Düzenleme Sayfası
![Düzenleme Sayfası](drawabele/editPage.png)

### Tools
![Tools](drawabele/tools.png)


### Filtreler
![Filtreler](drawabele/filter.png)

## 📂 Proje Yapısı

```plaintext
Tarator
├── Filters      # Filtreleme özellikleri
├── Tools        # Kırpma ve parlaklık araçları
├── Brush        # Fırça modu (çizim)
├── ViewModel    # Veri yönetimi
└── Resources    # Renk, tema ve diğer kaynaklar