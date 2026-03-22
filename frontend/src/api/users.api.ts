import { z } from 'zod'
import { userSchema } from '@/schemas/user.schema'
import type { User, CreateUserInput } from '@/schemas/user.schema'

const BASE = import.meta.env.VITE_API_URL

export async function fetchUsers(): Promise<User[]> {
    const res = await fetch(`${BASE}/users`)
    if (!res.ok) throw new Error('Erreur lors du chargement des utilisateurs')
    const data: unknown = await res.json()
    return z.array(userSchema).parse(data)
}

export async function createUser(input: CreateUserInput): Promise<User> {
    const res = await fetch(`${BASE}/users`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(input),
    })
    if (!res.ok) throw new Error('Erreur lors de la création')
    const data: unknown = await res.json()
    return userSchema.parse(data)
}