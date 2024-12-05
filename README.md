> para abrir use ctrl + shift + v

### Token:
```
{
    userId : number
    instructor : boolean
}
```
# /auth

- ## POST /auth ✅

  - ## Front-end
    ```
    {
        edv : string
        password : string
    }
    ```

  - ## Back-end
    ```
    {
        token : string
    }
    ```

# /register ✅

- ## POST /register

  ### Requirements:
  Bcrypt, 8 characters;
  
  > [!IMPORTANT]  
  > retornos do backend:  
  > 0: algum dos parâmetros é nulo  
  > 1: e-mail inválido  
  > 2: senha inválida  
  > 3: edv repetido  
  > 10: OK!  


  - ## Front-end
    ```
    {
        edv : string
        password : string
        name : string
        email : string
        intructor : boolean
    }
    ```

  - ## Back-end
    ```
    number
    ```

# /forum

- ## GET /forum

  - ## Front-end

    ### Query
    ```
    page
    title
    ```

  - ## Back-end
    ```
    [
        {
            id : number
            title : string
            image : string
            mainComment : string
        },
        ...
    ]
    ```

- ## POST /forum/create

  - ## Front-end
    ```

    {
        title : string
        id : number
        image : string
        description: string
    }

    ```

  - ## Back-end
    ```
    {
        section: {
            id : number
            title : string
            image : string
            description: string
        },
        message: string
    }
    ```

# /profile

- ## GET /profile/{id_user}

  - ## Front-end

    ### Path
    ```
    id_user - id do usuário
    ```

  - ## Back-end
    ```
    {
        edv : string
        password : string
        name : string
        email : string
        intructor : boolean
        github : string
        bio : string

        skills: [
            {
                id : number
                image : string
                title : string
            },
            ...
        ]

        interests : [
            {
                id : number
                name : string
            }
        ]
    }
    ```

> Obs: Para a criação do perfil, não é necassário uma bio ou um github, caso o usuário não tenha estes dados, eles voltarão como string vazia

- ## GET /profile/feedback/{id_user}

  - ## Front-end

    ### Path
    ```
    id_user - id do usuário
    ```

  - ## Back-end
    ```
    [
        {
            stars : number
            text : string
            public : boolean
            projectName : string
            user : {
                id : number
                image : string
                name : string
            }
        },
        ...
    ]
    ```

- ## GET /profile/interactions/{id_user}

  - ## Front-end

    ### Path
    ```
    id_user - id do usuário
    ```

  - ## Back-end
    ```
    [
        {
            type : string
            timestamp : Date
            content : {
                text : string
                username : string
                title : string
            }
        },
        ...
    ]
    ```

    > `type` será um de três valores:
    >
    > `like` - `content` terá o usuário dono da publicação que foi curtida.
    >
    > `comment` - um post do fórum, `content` terá o título do tópico e o texto da publicação, e `username` será nulo
    >
    > `feedback` - `content` terá o texto do feedback e o usuário pra quem ele foi dado. **Não retornar feedbacks privados.**

- ## PATCH /profile/{id_user}

  - ## Front-end
  
    ### Path:
    ```
    id_user
    ```

    ### Body:
    ```
    {
        password : string | null
        name : string | null
        email : string | null
        github : string | null
        bio : string | null
    }
    ```

  - ## Back-end
    ```
    {
        password : string
        name : string
        email : string
        github : string
        bio : string
    }
    ```

- ## POST /profile/skill/{id_user}

  - ## Front-end
  
    ### Path:
    ```
    id_user
    ```

    ### Body
    ```
    {
        id : number
    }
    ```

  - ## Back-end
    ```
    {
        id : number
        image : string
        title : string
    }
    ```

- ## DELETE /profile/skill/{id_user}
  
  - ## Front-end

    ### Path:
    ```
    id_user
    ```

    ### Body
    ```
    {
        id : number
    }
    ```

  - ## Back-end
    ```
    mensagem de sucesso
    ```

- ## POST /profile/interest/{id_user}

  - ## Front-end

    ### Path:
    ```
    id_user
    ```

    ### Body
    ```
    {
        name : string
    }
    ```

  - ## Back-end
    ```
    {
        id : number
        name : string
    }
    ```

- ## DELETE /profile/interest/{id_user}
  
  - ## Front-end

    ### Path:
    ```
    id_user
    ```

    ### Body
    ```
    {
        id : number
    }
    ```

  - ## Back-end
    ```
    mensagem de sucesso
    ```

# /skill

- ## POST /skill

  - ## Front-end
    ```
    {
        id : number
        image : string
        title : string
    }
    ```
    

  - ## Back-end
    ```
    {
        id : number
        image : string
        title : string
    }
    ```

- ## GET /skill
  
  - ## Front-end


  - ## Back-end
    ```
    [
        {
            id : number
            image : string
            title : string
        },
        ...
    ]
    ```

- ## DELETE /skill
  
  - ## Front-end
    ```
    {
        id : number
    }
    ```

  - ## Back-end
    ```
    mensagem de sucesso
    ```
