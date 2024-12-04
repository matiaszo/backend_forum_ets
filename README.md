> para abrir use ctrl + shift + v

### Token:
```
{
    userId : number
    instructor : boolean
}
```
# /auth ✅
## POST /auth ✅

### Front-end
```
{
    edv : string
    password : string
}
```

### Back-end
```
{
    token : string
}
```


# /register ✅
## POST /register ✅

### Requirements:
 Bcrypt, 8 characters;

### Front-end
```
{
    edv : string
    password : string
    name : string
    email : string
    intructor : boolean
}
```
> [!IMPORTANT]
> retornos do backend: 0: algum dos parâmetros é nulo | 1: e-mail inválido | 2: senha inválida | 3: edv repetido | 10: OK!  

### Back-end
```
{
    number
}
```
# /forum ✅
## GET /forum ✅

### Front-end

### Query
```
page
title
```

### Back-end
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

## POST /forum/create ✅

### Front-end
```

{
    title : string
    id : number
    image : string
    description: string
}

```

> [!IMPORTANT]
> retornos do backend: 1: algum dos parâmetros é nulo | 2: título já existe | 3: não é instrutor (403) | 10: OK!  

### Back-end
```
[
    number
]
```

# GET /profile

## Front-end

### Query
```
id
```

## Back-end
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
            // Completar depois
        },
        ...
    ]
}
```

# GET /profile/feedback

## Front-end

### Query
```
id
```

## Back-end
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

# GET /profile/interactions

## Front-end

### Query
```
id
```

## Back-end
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