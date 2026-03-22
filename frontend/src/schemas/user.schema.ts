import { z } from 'zod'

export const userSchema = z.object({
    id: z.number().int().positive(),
    firstname: z.string().min(1),
    lastname: z.string().min(1),
    email: z.string().email(),
})

export const createUserSchema = z.object({
    firstname: z.string().min(1, 'Le prénom est requis'),
    lastname: z.string().min(1, 'Le nom est requis'),
    email: z.string().email('Email invalide'),
    password: z.string().min(8, 'Le mot de passe doit faire au moins 8 caractères'),
})

export type User = z.infer<typeof userSchema>
export type CreateUserInput = z.infer<typeof createUserSchema>