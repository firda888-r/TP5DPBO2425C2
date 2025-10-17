**Janji**
Saya Firda Ridzki Utami dengan NIM 2401626 mengerjakan TP 5 dalam praktikum mata kuliah DPBO untuk keberkahannya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aaminn

Desain dan Alur kode program :

Program Toko Fashion ini dibuat sudah terhubung langsung dengan **database MYSQL** Struktur utamanya terdiri dari dua kelas, yaitu ProductMenu yang berperan sebagai tampilan utama dan pengendali logika CRUD. Pengguna dapat melakukan operasi tambah, ubah, hapus, dan menampilkan data produk melalui antarmuka grafis yang menampilkan tabel produk secara real time tanpa menggunakan ArrayList, melainkan langsung mengambil data dari database.

Data dari tabel product ditampilkan ke dalam JTable menggunakan method setTable(). Saat pengguna menekan tombol Add, sistem akan melakukan validasi untuk memastikan semua kolom telah diisi dan ID produk belum digunakan sebelumnya jika tidak sesuai maka saat user meng add data akan muncul promp id telah digunakan. Jika valid, data akan disimpan ke database menggunakan perintah INSERT. Data yang sudah tersimpan dapat diubah dengan menekan tombol Update, dan pengguna juga bisa menghapus data melalui tombol Delete setelah konfirmasi. Setelah setiap operasi, tabel akan diperbarui otomatis untuk menampilkan data terkini dari database lalu terdapat juga fitur validasi kolom kosong dengan dialog error saat melakukan insert atau update.

Selain fitur utama CRUD dan validasi, program ini juga dilengkapi dengan fitur clear form otomatis yang dijalankan setiap kali pengguna selesai melakukan operasi tambah, ubah, atau hapus data. Fitur ini berfungsi untuk mengosongkan semua kolom input, mengembalikan pilihan kategori ke default, serta menyembunyikan tombol Delete agar tampilan kembali ke mode penambahan data baru.

Screenshot hasil kode program :

Prompt error jika masih ada kolom input yang kosong saat insert/update
![kolom_kosong](https://github.com/user-attachments/assets/0dd3cc6c-0753-4c23-8c93-b44403d498d3)


Prompt error jika sudah ada ID yang sama saat insert
![id_sama](https://github.com/user-attachments/assets/2d5787b5-b4f4-474b-9f3c-fe10bcd99c2b)


Before add data
![before_add](https://github.com/user-attachments/assets/d181d712-bf9f-471e-b89c-037738a7d391)


After add data
![after_add](https://github.com/user-attachments/assets/56950ea1-283f-4b65-9b49-17b968da2d16)


Before update data
![before_update](https://github.com/user-attachments/assets/dcbaa191-8a72-46f8-a65d-4cd2a41474cf)


After update data
![after_update](https://github.com/user-attachments/assets/cda2af46-bd66-4bad-b346-73d86051c71e)

Before delete data
![before_delete](https://github.com/user-attachments/assets/14db29c1-5345-4c19-9608-980dd7099225)


After delete data
![after_delete](https://github.com/user-attachments/assets/c88cb909-01a7-437c-b319-5444b2b09e2a)


Delete data success
![succes_delete](https://github.com/user-attachments/assets/a6687a5c-aeee-476a-a994-3bc655f9ec84)


