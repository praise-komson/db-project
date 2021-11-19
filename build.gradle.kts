import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-beta5"
    id("com.squareup.sqldelight") version "1.5.2"
}

group = "me.suphon"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.arkivanov.decompose:decompose:0.4.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.4.0")
    implementation("com.squareup.sqldelight:jdbc-driver:1.5.2")
    implementation("com.zaxxer:HikariCP:5.0.0")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("org.litote.kmongo:kmongo:4.3.0")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "db-project"
            packageVersion = "1.0.0"
            macOS {
                packageName = "Doji"
                setDockNameSameAsPackageName = true
            }
        }
    }
}

sqldelight {
    database("Database") {
        packageName = "db"
        deriveSchemaFromMigrations = true
        dialect = "mysql"
    }
}
