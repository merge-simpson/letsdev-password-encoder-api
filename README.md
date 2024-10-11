# About API Module

## Bundled

- letsdev-password-encoder-port  
  `com.github.merge-simpson:letsdev-password-encoder-port:${version}`
- letsdev-password-encoder-exception  
  `com.github.merge-simpson:letsdev-password-encoder-exception:${version}`

## Installation

`build.gradle.kts`에서 다음과 같이 추가합니다. (번들된 의존성을 각각 추가하여도 됨.)

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") } // added
}

dependencies {
    implementation("com.github.merge-simpson:letsdev-password-encoder-api:0.1.0") // added
}
```

# Features

- Password Encoder Ports (independent of Spring Crypto, or any else libraries)
- Exceptions for options, generation, encryption of password encoders.
- Options for password encryption
- Apply auto configuration
  ([org.springframework.boot.autoconfigure.AutoConfiguration.imports 
  file](src%2Fmain%2Fresources%2FMATE-INF%2Fspring%2Forg.springframework.boot.autoconfigure.AutoConfiguration.imports))
  - Scan Properties
    ```yaml
    # 모든 속성은 선택적입니다.
    letsdev:
      password:
        default-encoder: bcrypt # support: bcrypt, argon2(same to argon2id), argon2id, argon2d
        bcrypt:
          strength: 10
        argon2:
          mode: argon2id
          salt-length: 16 # Unit: Bytes
          hash-length: 32 # Unit: Bytes
          parallelism: 1
          # memory-input: 생략 시 자동으로 계산됩니다. 메모리 비용 m ≥ 93750 ÷ ((3 × parallelism − 1) × α)
          iterations: 1
          alpha: 0.95
          memory-gain: 1.0
    ```